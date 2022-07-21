import * as React from 'react';
import InputAdornment from '@mui/material/InputAdornment';
import {TextField, Button, DialogTitle, DialogActions, Dialog} from "@mui/material";
import {useState} from 'react';

export default function SingleTransaction() {
    const [responseDetail, setResponseDetail] = useState({})
    const [responseISO, setResponseISO] = useState({value: ''})
    const [response, setResponse] = useState();
    const [open, setOpen] = React.useState(false);
    const textFiledStyle = {marginTop: '30px'}

    var messageRaw = {};
    const handleClickDetail = (e) => {
        if (document.getElementById('iso').value.toString() !== "") {
            messageRaw = {id: 0, value: document.getElementById('iso').value.toString()};
        }

        fetch("http://localhost:8080/singletransaction/parsemessage", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(messageRaw)
        }).then(res => res.json())
            .then(
                (result) => {
                    setResponseDetail(result)
                })
        messageRaw = {};
    }

    var listOfField = [];
    const handleClickISO = (e) => {
        for (let i = 0; i < 129; i++) {
            let id = i.toString();
            if (document.body.contains(document.getElementById(id)) && document.getElementById(id).value.toString() !== "") {
                let ele = {id: i, value: document.getElementById(id).value.toString()}
                listOfField.push(ele)
            }
        }
        fetch("http://localhost:8080/singletransaction/buildmessage", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(listOfField)
        }).then(resp => resp.json())
            .then(
                (result) => {
                    //console.log(result)
                    setResponseISO(result)
                })
        listOfField=[]
    }

    const handleClickSend = (e) => {
        if (document.getElementById('iso').value.toString() !== "") {
            messageRaw = {id: 0, value: document.getElementById('iso').value.toString()};
        }
        console.log(messageRaw);
        fetch("http://localhost:8080/singletransaction/send", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(messageRaw)
        }).then(res => res.json())
            .then(
                (result) => {
                    setResponse(result.message)
                    setOpen(true);
                })
    }

    return (
        <div className="row" style={textFiledStyle}>
            <div className="col-4">
                <h2>ISO message:</h2>
                    <TextField
                            id="iso"
                            style={{width: "80%"}}
                            variant="standard"
                            value={responseISO.value}
                            onChange = {(e) => setResponseISO({...responseISO, 'value':e.target.value})}
                            multiline
                    />
            </div>

            <div className='col-3' style={{marginTop: "60px"}}>
                <div>
                    <Button
                        style={{margin: '15px 15px', width: '50%'}}
                        type="submit" variant="contained"
                        onClick={handleClickDetail}
                    >
                        Detail >>>
                    </Button>
                </div>

                <div>
                    <Button
                        style={{margin: '15px 15px', width: '50%'}}
                        type="submit" variant="contained"
                        onClick={handleClickISO}
                    >
                        ISO &lt;&lt;&lt;
                    </Button>
                </div>
                <div>
                    <Button
                        style={{margin: '15px 15px', width: '50%'}}
                        type="submit" variant="contained"
                        onClick={handleClickSend}
                    >
                        Send Message
                    </Button>
                </div>
            </div>

            <div className='col-4'>
                <h2>ISO message detail:</h2>
                {responseDetail.length > 0 ? (<box>
                    {responseDetail.map((obj,index) => (
                        <TextField
                            key = {index}
                            id={obj.id}
                            style={{width: "100%"}}
                            InputProps={{
                                startAdornment: <InputAdornment position="start">{obj.id}</InputAdornment>,
                            }}
                            variant="standard"
                            value = {obj.value}
                            onChange = {(e) =>
                            {setResponseDetail(prevState => {
                                const newState = prevState.map(object => {
                                    if (object.id === obj.id){
                                        return {...object, value: e.target.value};
                                    }
                                    return object;
                                });
                                return newState;
                            })}}
                        />
                    ))}
                </box>) : (<TextField
                    style={{width: "100%"}}
                    variant="standard"
                />)}
            </div>
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
    );
}