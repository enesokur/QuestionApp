import { Grid, FormControl, FormLabel, Input, FormHelperText } from "@mui/material";
import { Button } from "@mui/material";
import axios from "axios";
import { createBrowserHistory } from "history";
import { useState } from "react";

function Auth(){
    const [userName,setUserName] = useState("");
    const [password,setPassword] = useState("");
    const history = createBrowserHistory();

    const sendRequest = async (request) => {
        try{
            const response = await axios.post("http://localhost:8080/auth/" + request,{
                userName: userName,
                password: password
            });
            localStorage.setItem("message",response.data.message);
            localStorage.setItem("userId",response.data.userId);
            localStorage.setItem("userName",response.data.userName);
            history.go(0);
        }
        catch(err){
            console.log(err);
        }
        
    }
    return(
       <Grid container direction="column" alignItems="center"
       justify="center">
            <FormControl>
                <FormLabel>Username</FormLabel>
                <Input onChange={(i) => setUserName(i.target.value)} />
            </FormControl>
            <FormControl>
                <FormLabel>Password</FormLabel>
                <Input onChange={(i) => setPassword(i.target.value)} />
            </FormControl>
            <Button onClick={() => sendRequest("register")} sx={{marginTop: 2}}variant="contained">Register</Button>
            <FormHelperText>Already have an account?</FormHelperText>
            <Button onClick={() => sendRequest("login")} variant="contained">Login</Button>
        </Grid>
    );
}
export default Auth;