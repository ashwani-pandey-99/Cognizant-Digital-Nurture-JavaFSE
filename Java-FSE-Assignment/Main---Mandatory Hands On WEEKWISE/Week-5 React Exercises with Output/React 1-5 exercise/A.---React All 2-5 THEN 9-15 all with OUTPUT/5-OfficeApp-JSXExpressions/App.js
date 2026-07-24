import React from "react";
// import office from "./office.jpg"; // Add your office.jpg image to src folder

function App() {
    const heading = "Office Space";
    const officeObj = {
        Name: "DBS",
        Rent: 50000,
        Address: "Chennai"
    };

    const officeList = [
        {
            Name: "DBS",
            Rent: 50000,
            Address: "Chennai"
        },
        {
            Name: "Regus",
            Rent: 70000,
            Address: "Bangalore"
        },
        {
            Name: "WeWork",
            Rent: 55000,
            Address: "Hyderabad"
        }
    ];

    return (
        <div style={{ marginLeft: "50px" }}>
            <h1>{heading}, at Affordable Range</h1>
            {/* <img src={office} width="25%" height="25%" alt="Office Space" /> */}
            <div style={{ width: "25%", height: "200px", backgroundColor: "#ddd", display: "flex", alignItems: "center", justifyContent: "center" }}>
                Office Image Placeholder
            </div>

            {/* Single Object */}
            <h2>Name: {officeObj.Name}</h2>
            <h3
                style={{
                    color: officeObj.Rent <= 60000 ? "red" : "green"
                }}
            >
                Rent: Rs. {officeObj.Rent}
            </h3>
            <h3>Address: {officeObj.Address}</h3>
            <hr />

            {/* List of Offices */}
            {officeList.map((item, index) => (
                <div key={index}>
                    <h2>Name: {item.Name}</h2>
                    <h3
                        style={{
                            color: item.Rent <= 60000 ? "red" : "green"
                        }}
                    >
                        Rent: Rs. {item.Rent}
                    </h3>
                    <h3>Address: {item.Address}</h3>
                    <br />
                </div>
            ))}
        </div>
    );
}

export default App;