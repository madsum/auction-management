import { useRef, useState, useEffect } from 'react';

import axios from 'axios';
const LOGIN_URL = 'http://localhost:8085/api/v1/auth/login';

const Login = props => {
    const userRef = useRef();
    const errRef = useRef();

    const [user, setUser] = useState('');
    const [pwd, setPwd] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [user, pwd])

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            
            const response = await axios.post(LOGIN_URL,
                JSON.stringify({userName: user, password: pwd }),
                {
                    headers: { 'Content-Type': 'application/json' },
                    withCredentials: true
                }
            );
            console.log("Login is successful!!!");
            console.log(JSON.stringify(response?.data?.token));
            const accessToken = response?.data?.token;
            const roles = response?.data?.roles;
            const logedUser = response?.data?.user;
            console.log("accessToken: "+accessToken);
            console.log("roles: "+roles);
            console.log("logedUser: "+JSON.stringify(logedUser));            
            setUser('');
            setPwd('');
            setSuccess(true);
            props.history.push("/auction",{ logedUser: logedUser}); 
        } catch (err) {
            if (!err?.response) {
                setErrMsg('No Server Response');
            } else if (err.response?.status === 400) {
                setErrMsg('Missing Username or Password');
            } else if (err.response?.status === 401) {
                setErrMsg('Unauthorized');
            } else {
                setErrMsg('Login Failed');
            }
          //  errRef.current.focus();
        }
    }

    return (
        <div className="center">
            {console.log("In the render success: "+JSON.stringify(success))}
            {success ? (
                <section style={{background: "white"}}>
                    <h1>You are logged in!</h1>
                    <br />
                    <p>
                        <a href="/auction">Go to Auction</a>
                    </p>
                </section>
            ) : (
                <section style={{background: "white"}}>
                    <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                    <h1>Sign In</h1>
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="username">Username:</label>
                        <input
                            type="text"
                            id="username"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setUser(e.target.value)}
                            value={user}
                            required
                        />

                        <label htmlFor="password">Password:</label>
                        <input
                            type="password"
                            id="password"
                            onChange={(e) => setPwd(e.target.value)}
                            value={pwd}
                            required
                        />
                        <label></label>    
                        <button class="btn btn-primary">Sign In</button>
                    </form>
                    <p>
                        Need an Account?<br />
                        <span className="line">
                            <a href="/">Sign Up</a>
                        </span>
                    </p>
                </section>
            )}
        </div>
    )
};

export default Login
