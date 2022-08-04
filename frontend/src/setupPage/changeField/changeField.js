import * as React from 'react';
import { Grid, TextField, Button } from '@mui/material';
import { useState, useEffect } from 'react';
import { Container } from '@mui/system';

export default function ChangeField() {
    const [balance, setBalance] = useState('');
    const [purchase, setPurchase] = useState('');
    const [withdraw, setWithdraw] = useState('');
    const [transfer, setTransfer] = useState('');
    const [changePIN, setChangePIN] = useState('');


    // Balance

    useEffect(() => {
        fetch("http://localhost:8080/balance/getfield", {
            method: "GET"
        }).then(res => res.json())
        .then(
            (result) => {
                // console.log(typeof(result));
                setBalance(JSON.stringify(result));
            })
    });

    const handleBalance = (e) => {
        e.preventDefault()
        const s = JSON.stringify(document.getElementById("BalanceTextfield").value.toString())
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/balance/postfield", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(s.substring(1, s.length - 1).replace(/\\/g, ""))
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
                setPurchase(JSON.stringify(result));
            })
    });

    const handlePurchase = (e) => {
        e.preventDefault()
        const s = JSON.stringify(document.getElementById("PurchaseTextfield").value.toString())
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/purchase/postfield", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(s.substring(1, s.length - 1).replace(/\\/g, ""))
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
                setWithdraw(JSON.stringify(result));
            })
    });

    const handleWithdraw = (e) => {
        e.preventDefault()
        const s = JSON.stringify(document.getElementById("WithdrawTextfield").value.toString())
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/withdraw/postfield", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(s.substring(1, s.length - 1).replace(/\\/g, ""))
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
                setChangePIN(JSON.stringify(result));
            })
    });

    const handleChangePIN = (e) => {
        e.preventDefault()
        const s = JSON.stringify(document.getElementById("ChangePINTextfield").value.toString())
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/changePIN/postfield", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(s.substring(1, s.length - 1).replace(/\\/g, ""))
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
                setTransfer(JSON.stringify(result));
            })
    });

    const handleTransfer = (e) => {
        e.preventDefault()
        const s = JSON.stringify(document.getElementById("TransferTextfield").value.toString())
        // console.log(s.substring(1, s.length - 1).replace(/\\/g, ""))
        fetch("http://localhost:8080/transfer/postfield", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(s.substring(1, s.length - 1).replace(/\\/g, ""))
        })
    }
    

    return (
        <Container>
        <Grid container spacing={4} style = {{marginTop : "20px"}}>
            
            <Grid item xs={6}>
                <TextField
                    id="BalanceTextfield"
                    label="Balance"
                    multiline
                    fullWidth
                    focused 
                    rows={8}
                    defaultValue = {balance}
                />
                <Button
                        style={{ margin: '15px 15px', width: '60%' }}
                        type="submit" variant="contained"
                        onClick={handleBalance}
                    >
                        Submit Balance
                </Button>
            </Grid>

            <Grid item xs={6}>
                <TextField
                    id="PurchaseTextfield"
                    label="Purchase"
                    multiline
                    fullWidth
                    focused 
                    rows={8}
                    defaultValue = {purchase}
                />
                <Button
                        style={{ margin: '15px 15px', width: '60%' }}
                        type="submit" variant="contained"
                        onClick={handlePurchase}
                    >
                        Submit Purchase
                </Button>
            </Grid>

            <Grid item xs={6}>
                <TextField
                    id="WithdrawTextfield"
                    label="Withdraw"
                    multiline
                    fullWidth
                    focused 
                    rows={8}
                    defaultValue = {withdraw}
                />
                <Button
                        style={{ margin: '15px 15px', width: '60%' }}
                        type="submit" variant="contained"
                        onClick={handleWithdraw}
                    >
                        Submit Withdraw
                </Button>
            </Grid>

            <Grid item xs={6}>
                <TextField
                    id="TransferTextfield"
                    label="Transfer"
                    multiline
                    fullWidth
                    focused 
                    rows={8}
                    defaultValue = {transfer}
                />
                <Button
                        style={{ margin: '15px 15px', width: '60%' }}
                        type="submit" variant="contained"
                        onClick={handleTransfer}
                    >
                        Submit Transfer
                </Button>
            </Grid>

            <Grid item xs={6}>
                <TextField
                    id="ChangePINTextfield"
                    label="ChangePIN"
                    multiline
                    fullWidth
                    focused 
                    rows={8}
                    defaultValue = {changePIN}
                />
                <Button
                        style={{ margin: '15px 15px', width: '60%' }}
                        type="submit" variant="contained"
                        onClick={handleChangePIN}
                    >
                        Submit ChangePIN
                </Button>
            </Grid>
            
        </Grid>
        </Container>
    );
}