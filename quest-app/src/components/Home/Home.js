import Post from "../Post/Post.js";
import { useState,useEffect } from "react";
import PostForm from "../Post/PostForm.js";

function Home(){
    const [postList, setPostList] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);
    const [error, setError] = useState(null);
    
    const loadPosts = () => {
        console.log("çalıştı");
        fetch("http://localhost:8080/posts")
        .then(response => response.json())
        .then((result) => {
            setIsLoaded(true);
            setPostList(result);
            
        },(error) => {
            setIsLoaded(true);
            setError(error);
        })
    }

    const savePost = (newPost) => {
        fetch('http://localhost:8080/posts', 
            {
                method: "POST",
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(newPost)
            })
            .then(response => response.json())
            .then((result) => {
                loadPosts();
            })
            .catch(err => console.log(err));
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