import Reservations from "./Reservations";

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
                    <Reservations reservation = {reservation} key = {reservation.id}/>
              )}
            </tbody>
          </table>
        </>
  );
};
export default ReservationsList;
