import * as React from 'react';
import { Box, Button, Dialog, DialogActions, DialogTitle, Container } from '@mui/material';
import TextField from '@mui/material/TextField';
import { useState } from 'react';
import { mapField } from '../../components/Field';

export default function Balance() {

    const [open, setOpen] = React.useState(false);
    const [response, setResponse] = useState('')

    const elements = [
        { id: 2, required: true },
        { id: 3, required: true },
        { id: 4, required: true },
        { id: 7, required: true },
        { id: 11, required: true },
        { id: 12, required: true },
        { id: 13, required: true },
        { id: 14, required: false },
        { id: 18, required: true },
        { id: 19, required: false },
        { id: 22, required: true },
        { id: 23, required: false },
        { id: 25, required: true },
        { id: 32, required: true },
        { id: 35, required: false },
        { id: 36, required: false },
        { id: 37, required: true },
        { id: 41, required: true },
        { id: 42, required: true },
        { id: 43, required: true },
        { id: 45, required: false },
        { id: 49, required: true },
        { id: 52, required: false },
        { id: 55, required: false },
        { id: 60, required: false },
        { id: 128, required: false },

    ]

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

    const handleClick = (e) => {
        e.preventDefault()
        for (let i = 0; i < 129; i++) {
            let id = "BL-" + i.toString();
            if (document.body.contains(document.getElementById(id)) && document.getElementById(id).value.toString() !== "") {
                let ele = { id: i, value: document.getElementById(id).value.toString() }
                fieldValue.push(ele)

            }
        }

        console.log(console.log(fieldValue))
        fetch("http://localhost:8080/balance/post", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(fieldValue)
        }).then(res => res.json())
            .then(
                (result) => {
                    console.log(result.message)
                    setResponse(result.message)
                    setOpen(true);
                })
        fieldValue = [{ id: 0, value: "0200" }, { id: 1, value: "1" }]
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
                        id={"BL-" + element.id.toString()}
                        label={mapField.get(element.id).name}
                        variant="outlined"
                        inputProps={{ maxLength: mapField.get(element.id).length }}
                        onKeyPress={event => typeField(event, mapField.get(element.id).type)}
                        required={element.required}
                    />
                ))
                }

                <div>
                    <Button
                        style={{ margin: '15px 15px', width: '40%' }}
                        type="submit" variant="contained"
                        onClick={handleClick}
                    >
                        Submit
                    </Button>

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
