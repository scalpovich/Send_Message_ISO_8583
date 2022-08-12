import * as React from 'react';
import {Box, Button, Dialog, DialogActions, DialogTitle, Container, CircularProgress, Backdrop} from '@mui/material';
import TextField from '@mui/material/TextField';
import { useState, useEffect } from 'react';
import { mapField } from '../../components/Field';

export default function Balance() {

    const [open, setOpen] = React.useState(false);
    const [response, setResponse] = useState('')
    const [errorArray, setErrorArray] = useState([])
    const [isError, setIsError] = useState([])
    const [loading, setLoading] = React.useState(false);
    const [elements, setElements] = useState([])
    var subErrorArray = []
    var subIsError = []

    var messageUpdate = "";

    useEffect(() => {
        fetch("http://localhost:8080/balance/getfield", {
            method: "GET"
        }).then(res => res.json())
        .then(
            (result) => {
                console.log((result));
                setElements(result);
            })
    });

    const textFiledStyle = { margin: '10px 30px' }

    let fieldValue = [{ id: 0, value: "0200" }]

    const onlyNumberKey = (event) => {
        if (!/[0-9]/.test(event.key)) {
            event.preventDefault();
        }
    }

    const onlyBinaryKey = (event) => {
        if (!/[0-1]/.test(event.key)) {
            event.preventDefault();
        }
    }

    const typeField = (event, type) => {
        if (type === "n")
            onlyNumberKey(event)
        if (type === "b")
            onlyBinaryKey(event)
    }

    const updateMessage = (e) => {

        let files = e.target.files;
        let reader = new FileReader()
        reader.readAsText(files[0])

        reader.onload = (e) => {
            if (e.target.result.length > 0) {
                messageUpdate = e.target.result
            }
        }
    }

    const handleClick = (e) => {
        e.preventDefault()

        if (messageUpdate.length > 0) {
            setLoading(true)
            let rawMessage = { id: -1 ,value: messageUpdate }
            console.log(rawMessage)
            fetch("http://localhost:8080/balance/postRawMessage", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(rawMessage)
            }).then(res => res.json())
                .then(
                    (result) => {
                        setLoading(false)
                        setResponse(result.message)
                        setOpen(true);
                    })
            return
        }

        let isValid = true

        for (let i = 0; i < elements.length; i++) {
            let e = document.getElementById("WD-" + elements[i].id.toString())
            if (elements[i].required === true && e.value.toString() === "") {
                subErrorArray[elements[i].id] = "This field can't be empty"
                setErrorArray(subErrorArray)
                subIsError[elements[i].id] = true
                setIsError(subIsError)
                isValid = false
                continue
            }

            let eProp = mapField.get(elements[i].id)
            if (e.value.toString() !== "" && eProp.variable === false && e.value.toString().length < eProp.length) {
                subErrorArray[elements[i].id] = `This field requires ${eProp.length} characters`
                setErrorArray(subErrorArray)
                subIsError[elements[i].id] = true
                setIsError(subIsError)
                isValid = false
                continue
            }
        }

        if (!isValid)
            return

        for (let i = 0; i < 129; i++) {
            let id = "WD-" + i.toString();
            if (document.body.contains(document.getElementById(id)) && document.getElementById(id).value.toString() !== "") {
                let ele = { id: i, value: document.getElementById(id).value.toString() }
                fieldValue.push(ele)
            }
        }

        console.log(fieldValue)
        setLoading(true)
        fetch("http://localhost:8080/balance/post", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(fieldValue)
        }).then(res => res.json())
            .then(
                (result) => {
                    setLoading(false)
                    setResponse(result.message)
                    setOpen(true);
                })
        fieldValue = [{ id: 0, value: "0200" }]
    }

    return (
        <Container >
            <h1>Balance</h1>
            <Box
                component="form"
                sx={{
                    '& .MuiTextField-root': { m: 1, width: '25ch' },
                }}
                noValidate
                autoComplete="on"
            >

                {elements.map(element => (
                    <TextField
                        style={textFiledStyle}
                        key={mapField.get(element.id).id}
                        id={"WD-" + element.id.toString()}
                        label={element.id.toString() + mapField.get(element.id).name}
                        variant="outlined"
                        inputProps={{ maxLength: mapField.get(element.id).length }}
                        onKeyPress={event => typeField(event, mapField.get(element.id).type)}
                        required={element.required}
                        helperText={errorArray[element.id]}
                        error={isError[element.id]}
                    />
                ))}

                <div style={{ textAlign: "left" }}>
                    <h3>Update message</h3>
                    <input type="file" name="message" accept="txt" onChange={(e) => updateMessage(e)} />
                </div>

                <div>
                    <Button
                        style={{ margin: '15px 15px', width: '40%' }}
                        type="submit" variant="contained"
                        onClick={handleClick}
                    >
                        Submit
                    </Button>

                    <Backdrop
                        sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
                        open={loading}
                    >
                        <CircularProgress color="inherit" />
                    </Backdrop>

                    <Dialog
                        open={open}
                        onClose={() => setOpen(false)}
                        aria-labelledby="alert-dialog-title"
                        aria-describedby="alert-dialog-description"
                    >
                        <DialogTitle id="alert-dialog-title">
                            {response}
                        </DialogTitle>

                        <DialogActions>
                            <Button onClick={() => setOpen(false)} autoFocus>
                                OK
                            </Button>
                        </DialogActions>
                    </Dialog>
                </div>
            </Box>
        </Container>
    );
}
