import * as React from 'react';
import { Container, Paper, TextField, Button, Box, Dialog, DialogActions, DialogContent ,DialogContentText ,DialogTitle  } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';


export default function Purchase() {
    const [open, setOpen] = React.useState(false);
    const[response,setResponse]=useState('')
    const textFiledStyle = { margin: '30px 30px' }



    let field = [{ id: 0, value: "0200" }]

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

    const handleClick = (e) => {
        e.preventDefault()
        for (let i = 0; i < 129; i++) {
            let id = "PC-" + i.toString();
            if (document.body.contains(document.getElementById(id)) && document.getElementById(id).value.toString() !== "") {
                let ele = { id: i, value: document.getElementById(id).value.toString() }
                field.push(ele)

            }
        }
        console.log("Try to fix")
        fetch("http://localhost:8080/purchase/post", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(field)
        }).then(res => res.json())
            .then(
                (result) => {
                    console.log(result.message)
                    setResponse(result.message)
                    setOpen(true);
                })
        field = [{ id: 0, value: "0200" }, { id: 1, value: "1" }]
    }

    return (
        <>
            <h1>Purchase</h1>
            <Box
                component="form"
                sx={{
                    '& .MuiTextField-root': { m: 1, width: '25ch' },
                }}
                noValidate
                autoComplete="off"
            >
                <div>
                    <TextField
                        style={textFiledStyle}
                        id="PC-2"
                        label="Primary Account Number"
                        variant="outlined"
                        type="text"
                        inputProps={{ maxLength: 19 }}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-3"
                        label="Processing Code"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyNumberKey}
                        inputProps={{ maxLength: 6, minLength: 6 }}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-4"
                        label="Transaction Amount"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyNumberKey}
                        inputProps={{ maxLength: 12, minLength: 12 }}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-7"
                        label="Transmission Date and Time (MMDDhhmmss)"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyNumberKey}
                        inputProps={{ maxLength: 10, minLength: 10 }}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-11"
                        label="System Trace Audit Number"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyNumberKey}
                        inputProps={{ maxLength: 6, minLength: 6 }}
                        required
                    />
                </div>

                <div>
                    <TextField
                        style={textFiledStyle}
                        id="PC-12"
                        label="Time, Local Transaction (hhmmss)"
                        variant="outlined"
                        fullWidth
                        type="text"
                        inputProps={{ maxLength: 6, minLength: 6 }}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-13"
                        label="Date, Local Transaction (MMDD)"
                        variant="outlined"
                        type="text"
                        onKeyPress={onlyNumberKey}
                        inputProps={{ maxLength: 4, minLength: 4 }}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-14"
                        label="Expiration Date (YYMM)"
                        variant="outlined"
                        fullWidth
                        inputProps={{ maxLength: 4, minLength: 4 }}
                        type="text"
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-18"
                        label="Merchant Type"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyNumberKey}
                        inputProps={{ maxLength: 4, minLength: 4 }}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-19"
                        label="Accepting Institution Country code"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyNumberKey}
                        inputProps={{ maxLength: 3, minLength: 3 }}
                    />
                </div>

                <div>
                    <TextField
                        style={textFiledStyle}
                        id="PC-22"
                        label="Point of Service Entry Mode Code"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyNumberKey}
                        inputProps={{ maxLength: 3, minLength: 3 }}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-22"
                        label="Card Sequence Number"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyNumberKey}
                        inputProps={{ maxLength: 3, minLength: 3 }}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-25"
                        label="Point of Service Condition Code"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                        onKeyPress={onlyNumberKey}
                        inputProps={{ maxLength: 2, minLength: 2 }}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-32"
                        label="Accepting Institution Identification Code"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                        onKeyPress={onlyNumberKey}
                        inputProps={{ maxLength: 11 }}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-35"
                        label="Track 2 data"
                        variant="outlined"
                        fullWidth
                        type="text"
                        inputProps={{ maxLength: 37 }}
                    />
                </div>

                <div>
                    <TextField
                        style={textFiledStyle}
                        id="PC-36"
                        label="Track 3 data"
                        variant="outlined"
                        fullWidth
                        type="text"
                        inputProps={{ maxLength: 104 }}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-37"
                        label="Retrieval Reference Number"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                        inputProps={{ maxLength: 12, minLength: 12 }}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-41"
                        label="Card Acceptor Terminal Identification"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                        inputProps={{ maxLength: 8, minLength: 8 }}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-42"
                        label="Card Acceptor Identification Code"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                        inputProps={{ maxLength: 15, minLength: 15 }}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-43"
                        label="Card Acceptor Name and Location"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                        inputProps={{ maxLength: 40, minLength: 40 }}
                    />
                </div>

                <div>
                    <TextField
                        style={textFiledStyle}
                        id="PC-45"
                        label="Track 1 data"
                        variant="outlined"
                        fullWidth
                        type="text"
                        inputProps={{ maxLength: 79 }}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-49"
                        label="Transaction Currency Code"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                        onKeyPress={onlyNumberKey}
                        inputProps={{ maxLength: 3, minLength: 3 }}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-52"
                        label=" Pin Data"
                        variant="outlined"
                        fullWidth
                        type="text"
                        inputProps={{ maxLength: 16, minLength: 16 }}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-55"
                        label="Chip Data"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyBinaryKey}
                        inputProps={{ maxLength: 255 }}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-60"
                        label="User defined field"
                        variant="outlined"
                        fullWidth
                        type="text"
                        inputProps={{ maxLength: 60 }}
                    />
                </div>


                <div>
                    <TextField
                        style={textFiledStyle}
                        id="PC-128"
                        label="Message Authentication Code"
                        variant="outlined"
                        fullWidth
                        type="text"
                        inputProps={{ maxLength: 16, minLength: 16 }}
                    />
                </div>


                <div>
                    <Button
                        style={{ margin: '15px 15px', width: '20%' }}
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

        </>

    );
}