
import CardContent from '@mui/material/CardContent';
import { Link } from "react-router-dom";
import Avatar from '@mui/material/Avatar';
import { OutlinedInput } from '@mui/material';
import { InputAdornment } from "@mui/material";
function Comment(props){
    const {text,userName,userId} = props;
    return(
        <div>
            <CardContent sx={{width: "50vw",}}>
                    <OutlinedInput sx={{
          "& .MuiInputBase-input.Mui-disabled": {
            WebkitTextFillColor: "#000000",
                    },
                    }}
                    disabled
                    multiline
                    inputProps={{maxLength:250}}
                    fullWidth
                    value={text}
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
                    >
                    </OutlinedInput>
                </CardContent>
        </div>
    );
}

export default Comment;