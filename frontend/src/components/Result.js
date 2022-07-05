import * as React from 'react';
import Navbar from "./Navbar";
import { useEffect, useState } from 'react';
import { Container, Paper, TextField, Button } from '@mui/material';

export default function Result() {
    const paperStyle = { padding: '50px 20px', width: 600, margin: "20px auto" }
    const [elements, setElements] = useState([])
    useEffect(() => {
        fetch("http://localhost:8080/purchase/get")
            .then(res => res.json())
            .then((result) => {
                console.log(result)
                setElements(result);
            }
            ).then(() => {
                // console.log(elements)
                
              })
    }, [])

    return (
        <>
            <Navbar />
            <Container>
                <h1>Result</h1>

                <Paper elevation={3} style={paperStyle}>

                    {elements.map(element => (
                        <Paper elevation={6} style={{ margin: "10px", padding: "15px", textAlign: "left" }} key={element.id}>
                            Id: {element.id}<br />
                            Value: {element.value}<br />
                        </Paper>
                    ))
                    }


                </Paper>
            </Container>
        </>

    );
}