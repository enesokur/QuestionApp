import { red } from '@mui/material/colors';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import Avatar from '@mui/material/Avatar';
import { Link } from "react-router-dom";
import OutlinedInput from '@mui/material/OutlinedInput';
import { Button, InputAdornment } from "@mui/material";
import { useState } from 'react';

function PostForm(props){
    const {userId,userName,savePost} = props;
    const [title,setTitle] = useState("");
    const [text,setText] = useState("");
    const [isSent,setIsSent] = useState(false);
    const handleTitle = (value) => {
        setTitle(value);
        setIsSent(false);
    }

    const handleText = (value) => {
        setText(value);
        setIsSent(false);
    }


    const handleSubmit = () => {
        savePost({ 
                title: title,
                text: text,
                userId: userId,
            });
        setIsSent(true);
        setText("");
        setTitle("");
    }
    return (
        <div>
            <Card sx={{width: "60vw",margin: 2}}>
                <CardHeader
                    avatar={
                    <Link to={"/users/" + userId} style={{
                        textDecoration: "none"
                    }}>
                    <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
                        {userName.charAt(0).toUpperCase()}
                    </Avatar>
                    </Link>
                    }
                    title={
                        <OutlinedInput
                            id="outlined-adornment-amount"
                            inputProps={{
                                maxLength: 25,

                            }}
                            fullWidth
                            placeholder="title"
                            multiline
                            onChange={(i) =>handleTitle(i.target.value)
                            }
                            value={!isSent? title : ""}
                        >
                        </OutlinedInput>
                    }
                />
                <CardContent>
                    <OutlinedInput
                        id="outlined-adornment-amount"
                        inputProps={{
                        maxLength: 250,

                        }}
                        fullWidth
                        placeholder="Text"
                        multiline
                        onChange={(i) => handleText(i.target.value)
                        }
                        value={!isSent? text : ""}
                        endAdornment= {
                            <InputAdornment>
                                <Button variant="contained" onClick={()=> handleSubmit()}>POST</Button>
                            </InputAdornment>
                        }
                    >
                    </OutlinedInput>
                </CardContent>
            </Card>
        </div>
    );
}

export default PostForm;