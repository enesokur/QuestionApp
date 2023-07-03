import Post from "../Post/Post.js";
import { useState,useEffect } from "react";
import PostForm from "../Post/PostForm.js";
import axios from "axios";

function Home(){
    const [postList, setPostList] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);
    const [error, setError] = useState(null);
    
    const loadPosts =  async () => {
        try{
            const response = await axios.get("http://localhost:8080/posts");
            setIsLoaded(true);
            setPostList(response.data);
        }
        catch(err){
            setIsLoaded(true);
            setError(error);
            console.log(err.message);
        }
    }

    const savePost = async (newPost) => {
        try{
            await axios.post("http://localhost:8080/posts",newPost);
            loadPosts();
        }
        catch(err){
            console.log(err);
        }
    }

    useEffect(() => {
        loadPosts();
    },[])

    if(!isLoaded){
        return (
            <div> Loading...</div>
        );
    }
    else if(error != null){
        return (
            <div>Error!!!</div>
        );
    }
    else{
        return(
            <div>
                <div style={{
                    display: "flex",
                    flexWrap: "wrap", 
                    justifyContent: "center", 
                    alignItems: "center",
                    backgroundColor: "#DBE8F1",
                    }}>
                        <PostForm userId={1} userName="user" savePost={savePost}/>
                        {postList.map((post) => {
                            return <Post title={post.title} text={post.text} userId={post.userId} userName={post.userName} postId={post.id} loadPosts={loadPosts} likes={post.likes}/>
                        })}
                </div>
            </div>
        );
    }
}

export default Home;