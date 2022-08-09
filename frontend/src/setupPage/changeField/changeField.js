import * as React from 'react';
import {Grid, TextField, Button, Switch, FormControlLabel} from '@mui/material';
import {useState, useEffect} from 'react';
import {Container} from '@mui/system';

export default function ChangeField() {
    const [toggle, setToggle] = useState(false);
    const [systemparam, setSystemParam] = useState('');
    const [dataelement, setDataElement] = useState('');
    const [balance, setBalance] = useState('');
    const [purchase, setPurchase] = useState('');
    const [withdraw, setWithdraw] = useState('');
    const [transfer, setTransfer] = useState('');
    const [changePIN, setChangePIN] = useState('');
    const [statement, setStatement] = useState('');
    var swt = false;
    // Balance
    useEffect(() => {
        fetch("http://localhost:8080/balance/getfield", {
            method: "GET"
        }).then(res => res.json())
            .then(
                (result) => {
                    // console.log(typeof(result));
                    setBalance(JSON.stringify(result, null, 2));
                })
    });

    const handleBalance = (e) => {
        e.preventDefault()
        const s = JSON.parse(document.getElementById("BalanceTextfield").value)
        // console.log(s)
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/balance/postfield", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(s, null, 2)
        })
    }

    // Purchase
    useEffect(() => {
        fetch("http://localhost:8080/purchase/getfield", {
            method: "GET"
        }).then(res => res.json())
            .then(
                (result) => {
                    // console.log(typeof(result));
                    setPurchase(JSON.stringify(result, null, 2));
                })
    });

    const handlePurchase = (e) => {
        e.preventDefault()
        const s = JSON.parse(document.getElementById("PurchaseTextfield").value)
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/purchase/postfield", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(s, null, 2)
        })
    }

    // Withdraw
    useEffect(() => {
        fetch("http://localhost:8080/withdraw/getfield", {
            method: "GET"
        }).then(res => res.json())
            .then(
                (result) => {
                    // console.log(typeof(result));
                    setWithdraw(JSON.stringify(result, null, 2));
                })
    });

    const handleWithdraw = (e) => {
        e.preventDefault()
        const s = JSON.parse(document.getElementById("WithdrawTextfield").value)
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/withdraw/postfield", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(s, null, 2)
        })
    }

    // ChangePIN
    useEffect(() => {
        fetch("http://localhost:8080/changePIN/getfield", {
            method: "GET"
        }).then(res => res.json())
            .then(
                (result) => {
                    // console.log(typeof(result));
                    setChangePIN(JSON.stringify(result, null, 2));
                })
    });

    const handleChangePIN = (e) => {
        e.preventDefault()
        const s = JSON.parse(document.getElementById("ChangePINTextfield").value)
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/changePIN/postfield", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(s, null, 2)
        })
    }

    // Transfer
    useEffect(() => {
        fetch("http://localhost:8080/transfer/getfield", {
            method: "GET"
        }).then(res => res.json())
            .then(
                (result) => {
                    // console.log(typeof(result));
                    setTransfer(JSON.stringify(result, null, 2));
                })
    });
    const handleTransfer = (e) => {
        e.preventDefault()
        const s = JSON.parse(document.getElementById("TransferTextfield").value)
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/transfer/postfield", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(s, null, 2)
        })
    }

    //Statement
    useEffect(() => {
        fetch("http://localhost:8080/statement/getfield", {
            method: "GET"
        }).then(res => res.json())
            .then(
                (result) => {
                    // console.log(typeof(result));
                    setStatement(JSON.stringify(result, null, 2));
                })
    });
    const handleStatement = (e) => {
        e.preventDefault()
        const s = JSON.parse(document.getElementById("StatementTextfield").value)
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/statement/postfield", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(s, null, 2)
        })
    }

    //Config DataElement

    useEffect(() => {
        fetch("http://localhost:8080/manage/configmapper/getfield", {
            method: "GET"
        }).then(res => res.json())
            .then(
                (result) => {
                    // console.log(typeof(result));
                    setDataElement(JSON.stringify(result, null, 2));
                })
    });
    const handleConfigMapper = (e) => {
        e.preventDefault()
        const s = JSON.parse(document.getElementById("ConfigMapperTextfield").value)
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/manage/configmapper/postfield", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(s, null, 2)
        })
    }

    // Config socket-thread-timeout
    useEffect(() => {
        fetch("http://localhost:8080/manage/socket/getsocket", {
            method: "GET"
        }).then(res => res.json())
            .then(
                (result) => {
                    // console.log(typeof(result));
                    setSystemParam(JSON.stringify(result, null, 2));
                })
    });
    const handleSocket = (e) => {
        e.preventDefault()
        const s = JSON.parse(document.getElementById("SocketTextfield").value)
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/manage/socket/postsocket", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(s, null, 2)
        }).then(setToggle(false))
            .then(
            fetch("http://localhost:8080/manage/switch", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(false)
        }))
    }

    // Switch
    const handleSwitch = () => {
        toggle ? setToggle(false):setToggle(true);
        fetch("http://localhost:8080/manage/switch", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(!toggle)
        });
    }

    return (
        <div className="row">
            <div className="col-1" style={{margin: "20px"}}>
                <FormControlLabel control={<Switch id="ConnectSwitch" checked={toggle} onClick={handleSwitch}/>}
                                  label="Connect"/>
            </div>
            <div className="col-10">
                <Grid style={{marginTop: "50px"}}>
                    <div className="row">
                        <Grid item xs={6} className="col-6">
                            <TextField
                                id="SocketTextfield"
                                label="socket-thread-timeout"
                                multiline
                                fullWidth
                                focused
                                rows={8}
                                defaultValue={systemparam}
                            />
                            <Button
                                style={{margin: '15px 15px', width: '60%'}}
                                type="submit" variant="contained"
                                onClick={handleSocket}
                            >
                                Submit
                            </Button>
                        </Grid>
                        <Grid item xs={6} className="col-6">
                            <TextField
                                id="BalanceTextfield"
                                label="Balance"
                                multiline
                                fullWidth
                                focused
                                rows={8}
                                defaultValue={balance}
                            />
                            <Button
                                style={{margin: '15px 15px', width: '60%'}}
                                type="submit" variant="contained"
                                onClick={handleBalance}
                            >
                                Submit Balance
                            </Button>
                        </Grid>
                    </div>

                    <div className="row">
                        <Grid item xs={6} className="col-6">
                            <TextField
                                id="PurchaseTextfield"
                                label="Purchase"
                                multiline
                                fullWidth
                                focused
                                rows={8}
                                defaultValue={purchase}
                            />
                            <Button
                                style={{margin: '15px 15px', width: '60%'}}
                                type="submit" variant="contained"
                                onClick={handlePurchase}
                            >
                                Submit Purchase
                            </Button>
                        </Grid>

                        <Grid item xs={6} className="col-6">
                            <TextField
                                id="WithdrawTextfield"
                                label="Withdraw"
                                multiline
                                fullWidth
                                focused
                                rows={8}
                                defaultValue={withdraw}
                            />
                            <Button
                                style={{margin: '15px 15px', width: '60%'}}
                                type="submit" variant="contained"
                                onClick={handleWithdraw}
                            >
                                Submit Withdraw
                            </Button>
                        </Grid>
                    </div>

                    <div className="row">
                        <Grid item xs={6} className="col-6">
                            <TextField
                                id="TransferTextfield"
                                label="Transfer"
                                multiline
                                fullWidth
                                focused
                                rows={8}
                                defaultValue={transfer}
                            />
                            <Button
                                style={{margin: '15px 15px', width: '60%'}}
                                type="submit" variant="contained"
                                onClick={handleTransfer}
                            >
                                Submit Transfer
                            </Button>
                        </Grid>

                        <Grid item xs={6} className="col-6">
                            <TextField
                                id="ChangePINTextfield"
                                label="ChangePIN"
                                multiline
                                fullWidth
                                focused
                                rows={8}
                                defaultValue={changePIN}
                            />
                            <Button
                                style={{margin: '15px 15px', width: '60%'}}
                                type="submit" variant="contained"
                                onClick={handleChangePIN}
                            >
                                Submit ChangePIN
                            </Button>
                        </Grid>
                    </div>
                    <div className="row">
                        <Grid item xs={6} className="col-6">
                            <TextField
                                id="StatementTextfield"
                                label="Statement"
                                multiline
                                fullWidth
                                focused
                                rows={8}
                                defaultValue={statement}
                            />
                            <Button
                                style={{margin: '15px 15px', width: '60%'}}
                                type="submit" variant="contained"
                                onClick={handleStatement}
                            >
                                Submit Statement
                            </Button>
                        </Grid>
                        <Grid item xs={6} className="col-6">
                            <TextField
                                id="ConfigMapperTextfield"
                                label="ConfigDataElement"
                                multiline
                                fullWidth
                                focused
                                rows={8}
                                defaultValue={dataelement}
                            />
                            <Button
                                style={{margin: '15px 15px', width: '60%'}}
                                type="submit" variant="contained"
                                onClick={handleConfigMapper}
                            >
                                Submit
                            </Button>
                        </Grid>
                    </div>
                </Grid>
            </div>
        </div>
    );
}