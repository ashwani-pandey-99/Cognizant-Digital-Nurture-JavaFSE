import React from "react";
import axios from "axios";
import Post from "./Post";

class Posts extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            posts: []
        };
    }

    loadPosts() {
        axios
            .get("https://jsonplaceholder.typicode.com/posts")
            .then(response => {
                const posts = response.data.map(
                    p => new Post(p.id, p.title, p.body)
                );

                this.setState({
                    posts: posts
                });
            })
            .catch(error => {
                console.log(error);
            });
    }

    componentDidMount() {
        this.loadPosts();
    }

    componentDidCatch(error, info) {
        alert("Something went wrong!");
        console.log(error);
        console.log(info);
    }

    render() {
        return (
            <div>
                <h1>Posts</h1>
                {
                    this.state.posts.map(post => (
                        <div key={post.id}>
                            <h3>{post.title}</h3>
                            <p>{post.body}</p>
                            <hr />
                        </div>
                    ))
                }
            </div>
        );
    }
}

export default Posts;