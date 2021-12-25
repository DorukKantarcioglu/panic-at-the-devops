const Reservations = (props) => {
    return (
        <div>
            <tr className="justify-content-start">
                <td>{props.reservation.date}</td>
                <td>{props.reservation.timeSlot}</td>
                <td>{props.reservation.place}</td>
            </tr>
        </div>
    );
};
export default Reservations;