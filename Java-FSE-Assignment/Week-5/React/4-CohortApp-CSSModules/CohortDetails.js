import React from 'react';
import styles from './CohortDetails.module.css';

const CohortDetails = ({ cohort }) => {
    return (
        <div className={styles.box}>
            <table>
                <tbody>
                    <tr>
                        <td colSpan="2"><strong>{cohort.name} - {cohort.technology}</strong></td>
                    </tr>
                    <tr>
                        <td>Start Date:</td>
                        <td>{cohort.startDate}</td>
                    </tr>
                    <tr>
                        <td>Current Status:</td>
                        <td style={{
                            color: cohort.currentStatus === "ongoing" ? "green" : "blue"
                        }}>
                            {cohort.currentStatus}
                        </td>
                    </tr>
                    <tr>
                        <td>Coach:</td>
                        <td>{cohort.coach}</td>
                    </tr>
                    <tr>
                        <td>Trainer:</td>
                        <td>{cohort.trainer}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
};

export default CohortDetails;