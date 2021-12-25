
const ReservationsList = ({reservations}) => {
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
