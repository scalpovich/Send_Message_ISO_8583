import React, {useState} from 'react'
import logo from '../assets/image/Logo.webp'
import '../styles/Navbar.css'

const Navbar =() => {
    return (
        <>
            <nav class="navbar navbar-expand-sm navbar-light bg-light">
                <div class="container">
                    <a href="#" class="navbar-brand"><img src={logo} alt="Logo"/></a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item"><a href="/" class="nav-link">Home</a></li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="" id="navbarDropdown-1" role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    Transaction
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="#">Single Transaction</a></li>
                                    <li><a class="dropdown-item" href="#">Batch Transaction</a></li>
                                    <li><a class="dropdown-item" href="#">Something else</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown-2" role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    Quick Transaction
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="/Balance">Balance</a></li>
                                    <li><a class="dropdown-item" href="/Purchase">Purchase</a></li>
                                    <li><a class="dropdown-item" href="#">Withdraw</a></li>
                                    <li><a class="dropdown-item" href="#">Transfer</a></li>
                                    <li><a class="dropdown-item" href="#">Change PIN</a></li>
                                </ul>
                            </li>
                            <li class="nav-item"><a href="" class="nav-link">ISO-8583</a></li>
                            <li class="nav-item"><a href="" class="nav-link">EN</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </>
    )

}


export default Navbar