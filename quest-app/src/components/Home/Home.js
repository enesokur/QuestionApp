import Post from "../Post/Post.js";
import { useState,useEffect } from "react";
import PostForm from "../Post/PostForm.js";

function Home(){
    const [postList, setPostList] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);
    const [error, setError] = useState(null);
    
    const loadPosts = () => {
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
    const addPosts = (newPost) => {
        setPostList([...postList,newPost]);
    }

    const removePost = (id) => {
        var index = postList.findIndex(post => post.id === id);
        const postListBeforeRemoveItem = postList.slice(0,index);
        const postListAfterRemoveItem = postList.slice(index+1,postList.length);
        removePostFromDb(id);
        setPostList([...postListBeforeRemoveItem,...postListAfterRemoveItem]);
        console.log(postList);
    }

    const removePostFromDb = (id) => {
        fetch('http://localhost:8080/posts/' + id, 
            {
                method: "DELETE",
            })
            .catch(err => console.log(err));
    }

    const addLike = (result,postId) => {
        setPostList(current => {
            let post = {};
            var index = 0;
            current.forEach(eachpost => {
                if(eachpost.id === postId){
                    post = eachpost;
                    index = current.indexOf(eachpost);
                }
            });
            if(post.likes != null){
                post.likes = [...post.likes,result];
            }
            else{
                post.likes = [result];
            }
            return [...current.slice(0,index),post,...current.slice(index+1,current.length)]
        });
    }
    
    const removeLike = (postId,likeIdToDelete) => {
        setPostList(current => {
            let post = {};
            var postIndex = 0;
            current.forEach(eachpost => {
                if(eachpost.id === postId ){
                    post = eachpost;
                    postIndex = current.indexOf(eachpost);
                }
            });
            if(post.likes != null){
                var likeIndex = 0;
                post.likes.forEach(like => {
                    if(like.id === likeIdToDelete){
                        likeIndex = post.likes.indexOf(like);
                    }
                });
                post.likes = [...post.likes.slice(0,likeIndex),...post.likes.slice(likeIndex+1,post.likes.length)]
            }
            return [...current.slice(0,postIndex),post,...current.slice(postIndex+1,current.length)]
        })
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
                        <PostForm userId={1} userName="user" addPosts={addPosts}/>
                        {postList.map((post) => {
                            return <Post title={post.title} text={post.text} userId={post.userId} userName={post.userName} postId={post.id} likes={post.likes} removePost={removePost} addLike={addLike} removeLike={removeLike}/>
                        })}
                </div>
            </div>
        );
    }
}

export default Home;