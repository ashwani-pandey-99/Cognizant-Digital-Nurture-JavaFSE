import React, { Component } from 'react';

class Home extends Component {
    render() {
        return (
            <div>
                <h3>Welcome to the Home Page of Student Management Portal</h3>
            </div>
        );
    }
}

class About extends Component {
    render() {
        return (
            <div>
                <h3>Welcome to the About Page of Student Management Portal</h3>
            </div>
        );
    }
}

class Contact extends Component {
    render() {
        return (
            <div>
                <h3>Welcome to the Contact Page of Student Management Portal</h3>
            </div>
        );
    }
}

function App() {
    return (
        <div className="container">
            <Home />
            <About />
            <Contact />
        </div>
    );
}

export default App;