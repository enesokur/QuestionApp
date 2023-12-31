
import CardContent from '@mui/material/CardContent';
import { Link } from "react-router-dom";
import Avatar from '@mui/material/Avatar';
import { InputAdornment } from "@mui/material";
import { OutlinedInput } from '@mui/material';
import { Button} from "@mui/material";
import { useState } from 'react';
import axios from 'axios';
function CommentForm(props){
    const {userName,userId,postId,loadComments} = props;
    const [text,setText] = useState("");
    const saveComment = async () => {
        try{
            await axios.post("http://localhost:8080/comments",{ 
            userId: userId,
            postId: postId,
            text: text
            }, {
                headers: {
                    "Authorization": localStorage.getItem("message")
                }
            });
            loadComments();
        }
        catch(err){
            console.log(err);
        }
    }

    const handleSubmit = () => {
        saveComment();
        setText("");
    }
    
    return(
        <div>
                <CardContent sx={{width: "50vw"}}>
                    <OutlinedInput
                    multiline
                    inputProps={{maxLength:250}}
                    fullWidth
                    value={text}
                    onChange={(i) => setText(i.target.value)}
                    startAdornment = {
                        <InputAdornment>
                            <Link to={"/users/" + userId} style={{
                                textDecoration: "none"
                            }}>
                            <Avatar sx={{ bgcolor: "#d93434" }} aria-label="recipe">
                                {userName.charAt(0).toUpperCase()}
                            </Avatar>
                            </Link>
                        </InputAdornment>
                    }
                    endAdornment = {
                        <InputAdornment>
                                <Button variant="contained" onClick={handleSubmit}>COMMENT</Button>
                        </InputAdornment>
                    }
                    >
                    </OutlinedInput>
                </CardContent>
        </div>
    );
}

export default CommentForm;