import { useRef, useState, useEffect } from "react";
import { faCheck, faTimes, faInfoCircle } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import axios from 'axios';
import * as React from 'react';

const   FULL_NAME_REGX = /^[a-zA-Z0-9 ]+$/;
const EMAIL_REGX = /^(?![\w\.@]*\.\.)(?![\w\.@]*\.@)(?![\w\.]*@\.)\w+[\w\.]*@[\w\.]+\.\w{2,}$/;
const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const REGISTER_URL = 'http://localhost:8083/api/v1/register';

const RegisterUser = () => {
    const userRef = useRef();
    const errRef = useRef();

    const [userName, setUserName] = useState('');
    const [validName, setValidName] = useState(false);
    const [userFocus, setUserFocus] = useState(false);

    const [fullName, setFullName] = useState('');
    const [validFullName, setValidFullName] = useState(false);

    const [email, setEmail] = useState('');
    const [validEmail, setValidEmail] = useState(false);

    const [password, setPassword] = useState('');
    const [validPwd, setValidPwd] = useState(false);
    const [pwdFocus, setPwdFocus] = useState(false);

    const [matchPwd, setMatchPwd] = useState('');
    const [validMatch, setValidMatch] = useState(false);
    const [matchFocus, setMatchFocus] = useState(false);

    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    const [role, setRole] = React.useState('BUYER');

    const handleChange = (event) => {
        setRole(event.target.value);
        console.log(role);
    };

    useEffect(() => {
        setValidFullName(FULL_NAME_REGX.test(fullName));
    }, [fullName])

    useEffect(() => {
        setValidEmail(EMAIL_REGX.test(email));
    }, [email])

    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setValidName(USER_REGEX.test(userName));
    }, [userName])

    useEffect(() => {
        setValidPwd(PWD_REGEX.test(password));
        setValidMatch(password === matchPwd);
    }, [password, matchPwd])

    useEffect(() => {
        setErrMsg('');
    }, [userName, password, matchPwd])

    const handleSubmit = async (e) => {
        e.preventDefault();
        const v1 = USER_REGEX.test(userName);
        const v2 = PWD_REGEX.test(password);
        if (!v1 || !v2) {
            setErrMsg("Invalid Entry");
            return;
        }
        try {
            let user = {
                    userName: userName, 
                    password: password,
                    fullName: fullName,
                    email: email,
                    authorities: [{"roleCode":role,"authority":role}],
                    role: role
                }
            console.log("user: "+JSON.stringify(user));
            const response = await axios.post(REGISTER_URL,
                user,
                {
                    headers: { 'Content-Type': 'application/json' },
                    withCredentials: true
                }
            );
            console.log(JSON.stringify(response?.data));
            console.log(JSON.stringify(response));
            setSuccess(true);
            //clear state and controlled inputs
            //need value attrib on inputs for this
            setUserName('');
            setPassword('');
            setMatchPwd('');
        } catch (err) {
            if (!err?.response) {
                setErrMsg('No Server Response');
            } else if (err.response?.status === 409) {
                setErrMsg('Username Taken');
            } else {
                setErrMsg('Registration Failed')
            }
            errRef.current.focus();
        }
    }

    return (
        <>
            {success ? (
                <section style={{background: "white"}}>
                    <h1>Success!</h1>
                    <p>
                        <a href="/signin">Sign In</a>
                    </p>
                </section>
            ) : (
                <section style={{background: "white"}}>
                    <p>
                        Already registered?<br />
                        <span className="line">
                            <a href="/signin">Sign In</a>
                        </span>
                    </p>
                    <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                    <h1>Register</h1>
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="fullName">
                            Full Name:
                            <FontAwesomeIcon icon={faCheck} className={validFullName ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={validFullName || !fullName ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="text"
                            id="fullName"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setFullName(e.target.value)}
                            value={fullName}
                            required
                            aria-describedby="uidnote"
                            onFocus={() => setValidFullName(true)}
                        />
                            <p id="uidnote" className={ fullName && !validFullName ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            4 to 50 letters.<br />
                            All small and capital letters allowed.
                        </p>
                        

                        <label htmlFor="username">
                            Username:
                            <FontAwesomeIcon icon={faCheck} className={validName ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={validName || !userName ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="text"
                            id="username"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setUserName(e.target.value)}
                            value={userName}
                            required
                            aria-invalid={validName ? "false" : "true"}
                            aria-describedby="uidnote"
                            onFocus={() => setUserFocus(true)}
                            onBlur={() => setUserFocus(false)}
                        />
                        <p id="uidnote" className={userFocus && userName && !validName ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            4 to 24 characters.<br />
                            Must begin with a letter.<br />
                            Letters, numbers, underscores, hyphens allowed.
                        </p>

                        <label htmlFor="email">
                            Email:
                            <FontAwesomeIcon icon={faCheck} className={validEmail ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={validEmail || !email ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="text"
                            id="email"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setEmail(e.target.value)}
                            value={email}
                            required
                            aria-describedby="uidnote"
                            onFocus={() => setValidEmail(true)}
                        />
                            <p id="uidnote" className={ email && !validEmail ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            4 to 50 letters.<br />
                            All small and capital letters allowed.
                        </p>


                        <label htmlFor="password">
                            Password:
                            <FontAwesomeIcon icon={faCheck} className={validPwd ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={validPwd || !password ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="password"
                            id="password"
                            onChange={(e) => setPassword(e.target.value)}
                            value={password}
                            required
                            aria-invalid={validPwd ? "false" : "true"}
                            aria-describedby="pwdnote"
                            onFocus={() => setPwdFocus(true)}
                            onBlur={() => setPwdFocus(false)}
                        />
                        <p id="pwdnote" className={pwdFocus && !validPwd ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            8 to 24 characters.<br />
                            Must include uppercase and lowercase letters, a number and a special character.<br />
                            Allowed special characters: <span aria-label="exclamation mark">!</span> <span aria-label="at symbol">@</span> <span aria-label="hashtag">#</span> <span aria-label="dollar sign">$</span> <span aria-label="percent">%</span>
                        </p>


                        <label htmlFor="confirm_pwd">
                            Confirm Password:
                            <FontAwesomeIcon icon={faCheck} className={validMatch && matchPwd ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={validMatch || !matchPwd ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="password"
                            id="confirm_pwd"
                            onChange={(e) => setMatchPwd(e.target.value)}
                            value={matchPwd}
                            required
                            aria-invalid={validMatch ? "false" : "true"}
                            aria-describedby="confirmnote"
                            onFocus={() => setMatchFocus(true)}
                            onBlur={() => setMatchFocus(false)}
                        />
                        <p id="confirmnote" className={matchFocus && !validMatch ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            Must match the first password input field.
                        </p>

                        <label >
                            Select user type:
                        </label>
                        <label>
                            <select id="select-size" value={role} onChange={handleChange}>
                            <option value="ADMIN">Admin</option>
                            <option value="BUYER">Buyer</option>
                            <option value="SELLER">Seller</option>
                            </select>
                        </label>
                        <p>Selected {role}!</p>
                      

                        <button disabled={ !validEmail || !validFullName || !validName || !validPwd || !validMatch ? true : false}>Sign Up</button>
                    </form>
                </section>
            )}
        </>
    )
}

export default RegisterUser
