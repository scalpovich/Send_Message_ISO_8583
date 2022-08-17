import * as React from 'react';
import {Box, Button, Typography, Grid, Card, CardContent, TextField, DialogTitle, DialogActions, Dialog} from '@mui/material';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import {useState} from "react";


export default function BatchTransaction() {
    const [transaction, setTransaction] = useState('');
    const [config, setConfig] = useState('');
    const [response, setResponse] = useState('')
    const [open, setOpen] = React.useState(false);

    let value = "";
    const handleChange = (event) => {
        value = event.target.value;
        setTransaction(value);
        fetch('http://localhost:8080/batchmessage/gettransaction',{
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(value)
        }).then(res => res.json())
            .then(
                (result) => {
                    // console.log(typeof(result));
                    setConfig(JSON.stringify(result, null, 2));
                })
        value = ""
    };

    let data =[]
    const handleBatchTransaction = () => {
        console.log(transaction);
        if (document.getElementById('number-of-transaction').value.toString() !== "" && transaction !== "") {
            data.push({id: 0, value: document.getElementById("number-of-transaction").value.toString()});
            data.push({id: 1, value: transaction});
            data.push({id: 2, value: document.getElementById("content-transaction").value.toString()});

            fetch("http://localhost:8080/batchmessage/post", {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(data)
            }).then(res => res.json())
                .then(
                    (result) => {
                        setResponse(result)
                        setOpen(true)
                    })
            data = [];
        }
    }

    return (
        <div className="batch-transaction" style={{marginTop: '30px'}}>
            <Grid>
                <Card style={{maxWidth: 450, padding: "20px 5px", margin: "0 auto"}}>
                    <CardContent>
                        <Typography gutterBottom variant="h5">
                            Batch Transaction
                        </Typography>
                        <form>
                            <Grid container spacing={5}>
                                <Grid item xs={12}>
                                    <TextField id = "number-of-transaction"
                                               type="number"
                                               InputProps={{inputProps: {min: 1}}}
                                               placeholder="Enter number of transaction"
                                               label="Number of Transaction"
                                               variant="outlined"
                                               fullWidth
                                               required/>
                                </Grid>

                                <Grid item xs={12}>
                                    <Box sx={{minWidth: 120}}>
                                        <FormControl fullWidth>
                                            <InputLabel id="Transaction">Transaction</InputLabel>
                                            <Select
                                                labelId="transaction"
                                                id="transaction"
                                                label="Transaction"
                                                value={transaction}
                                                onChange={handleChange}
                                                placeholder="Transaction"
                                            >
                                                <MenuItem value={1}>Balance</MenuItem>
                                                <MenuItem value={2}>Purchase</MenuItem>
                                                <MenuItem value={3}>Withdraw</MenuItem>
                                                <MenuItem value={4}>Transfer</MenuItem>
                                                <MenuItem value={5}>Change PIN</MenuItem>
                                                <MenuItem value={6}>Statement</MenuItem>
                                            </Select>
                                        </FormControl>
                                    </Box>
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField id = "content-transaction"
                                               placeholder="config transaction"
                                               label="Config Message"
                                               variant="outlined"
                                               fullWidth
                                               multiline
                                               focused
                                               defaultValue={config}
                                               rows={8}
                                               />
                                </Grid>
                                <Grid item xs={12}>
                                    <Button type="submit"
                                            variant="contained"
                                            color="primary"
                                            fullWidth
                                            onClick={handleBatchTransaction}
                                    >Submit</Button>
                                </Grid>
                            </Grid>
                        </form>
                    </CardContent>
                </Card>
            </Grid>
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
