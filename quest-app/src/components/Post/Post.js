import { useState } from "react";
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ChatBubbleOutlineIcon from '@mui/icons-material/ChatBubbleOutline';
import { Link } from "react-router-dom";
import Comment from "../Comment/Comment.js";
import CommentForm from "../Comment/CommentForm.js";
import { useEffect } from "react";
import ClearIcon from '@mui/icons-material/Clear';
import axios from "axios";

const ExpandMore = styled((props) => {
    const { expand, ...other } = props;
    return <IconButton {...other} />;
  })(({ theme, expand }) => ({
    transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
      duration: theme.transitions.duration.shortest,
    }),
  }));

function Post(props){

    const [expanded, setExpanded] = useState(false);
    const [liked, setLiked] = useState(false);
    const {title,text,userId,userName, postId,loadPosts,likes} = props;
    const [commentList,setCommentList] = useState([]);
    const [likeCount, setLikeCount] = useState(likes.length);
    const [currentLike,setCurrentLike] = useState({});

    const loadComments = async () => {
        try{
            const response = await axios.get("http://localhost:8080/comments?postId=" + postId,{
                headers: {
                    "Authorization": localStorage.getItem("message")
                }
            });
            setCommentList(response.data);
        }
        catch(err){
            console.log(err);
        }
    }

    const handleExpandClick = () => {
        setExpanded(!expanded);
        loadComments();
    };


    const handleLike = () => {
        setLiked(!liked);
        if(liked === false){
            setLikeCount(likeCount +1);
            saveLike();
        }
        else{
            setLikeCount(likeCount -1);
            deleteLike()
        }
    }

    const checkLikes = () => {
        var like = likes.find(like => "" + like.userId === localStorage.getItem("userId"))
        if(like != null){
            setLiked(true)
        }
        findLike();
    }

    const saveLike = async () => {
        try{
            const response = await axios.post("http://localhost:8080/likes",{ 
            postId: postId,
            userId: localStorage.getItem("userId"),
            }, {
                headers:{
                    "Authorization": localStorage.getItem("message")
                }
            });
            setCurrentLike(response.data);
        }
        catch(err){
            console.log(err);
        }
        /*fetch('http://localhost:8080/likes', 
            {
                method: "POST",
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ 
                    postId: postId,
                    userId: userId,
                })
            })
            .then(response => response.json())
            .then(result => {
                setCurrentLike(result);
            })*/
    }
    const findLike = () => {
        const like = likes.find(like => "" + like.userId === localStorage.getItem("userId"));
        setCurrentLike(like);
    }
    const deleteLike = async () => {
        try{
            await axios.delete("http://localhost:8080/likes/" + currentLike.id,{
                headers:{
                    "Authorization": localStorage.getItem("message")
                }
            });
        }
        catch(err){
            console.log(err);
        }
        /*
        fetch('http://localhost:8080/likes/' + currentLike.id, 
            {
                method: "DELETE",
            })
            .catch(err => console.log(err)); */   
    }

    const removePost = async (id) => {
        try{
            await axios.delete("http://localhost:8080/posts/" + id,{
                headers:{
                    "Authorization": localStorage.getItem("message")
                }
            });
            loadPosts();
        }
        catch(err){
            console.log(err);
        }
    }

    useEffect(() => {
        checkLikes();
    },[])

    return (
        <div>
            <Card sx={{width: "60vw",margin: 2}}>
                <IconButton sx={{float: "right"}} onClick={() => removePost(postId)}>
                        <ClearIcon/>
                </IconButton>
                <CardHeader
                    avatar={
                    <Link to={"/users/" + userId} style={{
                        textDecoration: "none"
                    }}>
                    <Avatar sx={{ bgcolor: "#d93434" }} aria-label="recipe">
                        {userName.charAt(0).toUpperCase()}
                    </Avatar>
                    </Link>
                    }
                    title={title}
                />
                <CardContent>
                    {text}
                </CardContent>
                <CardActions disableSpacing>
                    <IconButton 
                    onClick={handleLike}
                    aria-label="add to favorites">
                    <FavoriteIcon sx={ liked? {color: "red"} : null}/>
                    </IconButton>
                    {likeCount}
                    <ExpandMore
                    expand={expanded}
                    onClick={handleExpandClick}
                    aria-expanded={expanded}
                    aria-label="show more"
                    >
                    <ChatBubbleOutlineIcon />
                    </ExpandMore>
                </CardActions>
                <Collapse in={expanded} timeout="auto" unmountOnExit>
                    <CardContent>
                        {commentList.map((comment) => {
                            return <Comment commentId={comment.id} text={comment.text} userName={comment.userName} userId={comment.userId}/>
                        })}
                        <CommentForm userName={localStorage.getItem("userName")} userId={localStorage.getItem("userId")} postId={postId} loadComments={loadComments}/>
                    </CardContent>
                </Collapse>
            </Card>
        </div>
    );
}

export default Post;