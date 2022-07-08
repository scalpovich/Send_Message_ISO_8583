import * as React from 'react';
import { Box, Button, Dialog, DialogActions, DialogContent ,DialogContentText ,DialogTitle } from '@mui/material';
import Navbar from '../../components/Navbar';
import { useNavigate } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import { useState } from 'react';

export default function Balance() {

    const [open, setOpen] = React.useState(false);
    const[response,setResponse]=useState('')
    const textFiledStyle = { margin: '30px 30px' };
    let field = [{ id: 1, value: "0200" }];

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
        for (let i = 2; i <= 128; i++) {
            let id = "PC-" + i.toString();
            if (document.body.contains(document.getElementById(id)) && document.getElementById(id).value.toString() !== "") {
                let ele = { id: i, value: document.getElementById(id).value.toString() }
                field.push(ele)
            }
        }

        fetch("http://localhost:8080/balance/post", {
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
    }

    return (
        <>
            <h1 style={{ margin: '15px 15px' }}
            >Balance</h1>
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
                        label="F-2"
                        variant="outlined"
                        type="text"
                        inputProps={{ maxLength: 19 }}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-3"
                        label="F-3"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyNumberKey}
                        // inputProps={{ maxLength: 6, minLength: 6 }}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-4"
                        label="F-4"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyNumberKey}
                        // inputProps={{ maxLength: 12, minLength: 12 }}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-5"
                        label="F-5"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyNumberKey}
                        // inputProps={{ maxLength: 10, minLength: 10 }}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-7"
                        label="F-7"
                        variant="outlined"
                        fullWidth
                        type="text"
                        onKeyPress={onlyNumberKey}
                        // inputProps={{ maxLength: 6, minLength: 6 }}
                        required
                    />
                </div>

                <div>
                    <TextField
                        style={textFiledStyle}
                        id="PC-9"
                        label="F-9"
                        variant="outlined"
                        fullWidth
                        type="text"
                        // inputProps={{maxLength: 6, minLength: 6}}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-11"
                        label="F-11"
                        variant="outlined"
                        type="text"
                        // onKeyPress={onlyNumberKey}
                        // inputProps={{maxLength: 4, minLength: 4}}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-12"
                        label="F-12"
                        variant="outlined"
                        fullWidth
                        // inputProps={{maxLength: 4, minLength: 4}}
                        type="text"
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-13"
                        label="F-13"
                        variant="outlined"
                        fullWidth
                        type="text"
                        // onKeyPress={onlyNumberKey}
                        // inputProps={{maxLength: 4, minLength: 4}}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-15"
                        label="F-15"
                        variant="outlined"
                        fullWidth
                        type="text"
                    // onKeyPress={onlyNumberKey}
                    // inputProps={{maxLength: 3, minLength: 3}}
                    />
                </div>

                <div>
                    <TextField
                        style={textFiledStyle}
                        id="PC-18"
                        label="F-18"
                        variant="outlined"
                        fullWidth
                        type="text"
                        // onKeyPress={onlyNumberKey}
                        // inputProps={{maxLength: 3, minLength: 3}}
                        required
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-22"
                        label="F-22"
                        variant="outlined"
                        fullWidth
                        type="text"
                    // onKeyPress={onlyNumberKey}
                    // inputProps={{maxLength: 3, minLength: 3}}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-25"
                        label="F-25"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                    // onKeyPress={onlyNumberKey}
                    // inputProps={{maxLength: 2, minLength: 2}}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-32"
                        label="F-32"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                    // onKeyPress={onlyNumberKey}
                    // inputProps={{maxLength: 11}}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-35"
                        label="F-35"
                        variant="outlined"
                        fullWidth
                        type="text"
                    // inputProps={{maxLength: 37}}
                    />
                </div>

                <div>
                    <TextField
                        style={textFiledStyle}
                        id="PC-36"
                        label="F-36"
                        variant="outlined"
                        fullWidth
                        type="text"
                    // inputProps={{maxLength: 104}}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-37"
                        label="F-37"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                    // inputProps={{maxLength: 12, minLength: 12}}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-41"
                        label="F-41"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                    // inputProps={{maxLength: 8, minLength: 8}}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-42"
                        label="F-42"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                    // inputProps={{maxLength: 15, minLength: 15}}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-43"
                        label="F-43"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                    // inputProps={{maxLength: 40, minLength: 40}}
                    />
                </div>

                <div>
                    <TextField
                        style={textFiledStyle}
                        id="PC-49"
                        label="F49"
                        variant="outlined"
                        fullWidth
                        type="text"
                    // inputProps={{maxLength: 79}}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-50"
                        label="F-50"
                        variant="outlined"
                        fullWidth
                        required
                        type="text"
                    // onKeyPress={onlyNumberKey}
                    // inputProps={{maxLength: 3, minLength: 3}}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-52"
                        label="F-52"
                        variant="outlined"
                        fullWidth
                        type="text"
                    // inputProps={{maxLength: 16, minLength: 16}}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-63"
                        label="F-63"
                        variant="outlined"
                        fullWidth
                        type="text"
                    // onKeyPress={onlyBinaryKey}
                    // inputProps={{maxLength: 255}}
                    />

                    <TextField
                        style={textFiledStyle}
                        id="PC-128"
                        label="F-128"
                        variant="outlined"
                        fullWidth
                        type="text"
                    //inputProps={{maxLength: 60}}
                    />
                </div>


                {/*<div>*/}
                {/*    <TextField*/}
                {/*        style={textFiledStyle}*/}
                {/*        id="PC-128"*/}
                {/*        label="Message Authentication Code"*/}
                {/*        variant="outlined"*/}
                {/*        fullWidth*/}
                {/*        type="text"*/}
                {/*        inputProps={{maxLength: 16, minLength: 16}}*/}
                {/*    />*/}
                {/*</div>*/}


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
