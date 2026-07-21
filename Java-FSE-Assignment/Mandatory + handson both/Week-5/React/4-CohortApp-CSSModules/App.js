import React from 'react';
import CohortDetails from './CohortDetails';

const sampleCohorts = [
    {
        name: "INTADMDF10",
        technology: ".NET FSD",
        startDate: "01-Jun-2024",
        currentStatus: "ongoing",
        coach: "Aditya",
        trainer: "John"
    },
    {
        name: "INTADMDF11",
        technology: "Java FSD",
        startDate: "15-May-2024",
        currentStatus: "completed",
        coach: "Priya",
        trainer: "Sarah"
    }
];

function App() {
    return (
        <div>
            <h1>Cohort Details</h1>
            {sampleCohorts.map((cohort, index) => (
                <CohortDetails key={index} cohort={cohort} />
            ))}
        </div>
    );
}

export default App;