
import {useEffect, useState} from "react";
import ReservationService from "../../../service/ReservationService";

const ReservationsList = ({}) => {
    const[reservations, setReservations] = useState([
        {
            date: "22.12.2021 ",
            timeSlot: " 15:00 ",
            place: "Main Campus Hall",
            type: "Sport Center",
            userId: 21901009,
            id: 323,
        },
        {
            date: "30.11.2021",
            timeSlot: "21:30 ",
            place: "Library ( Main Hall ) ",
            type: "Library",
            userId: 21901009,
            id: 3424,
        },
    ])
    const createReservation = (newReservation) => {

        setReservations([...reservations, newReservation])
    }
    async function fetchReservations(){

        const response = await ReservationService.getReservations();
        {response&& response.map(response0 =>
            setReservations([response0])
        )}
    }
    useEffect( async () => {
        await fetchReservations();
    }, [])

    return (
        <>
            <div className="row d-flex justify-content-start">
                <h5> Upcoming Reservations </h5>
            </div>
            <table
                className="border"
                style={{ position: "relative", width: "100%", marginLeft: "20px" }}
            >
                <thead>
                <tr>
                    <th> Date</th>
                    <th> Time Slot</th>
                    <th> Place </th>
                </tr>
                </thead>
                <tbody>
                {reservations.map(reservation =>
                    <tr>
                        <td>{reservation.date}</td>
                        <td>{reservation.timeSlot}</td>
                        <td>{reservation.place}</td>
                    </tr>
                )}

                </tbody>
            </table>
        </>
    );
};
export default ReservationsList;
