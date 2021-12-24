const ReservationsList = (props) => {
  const reservations = [
    {
      date: "Today ",
      timeSlot: " 15:00 ",
      place: "Main Campus Hall",
      type: "Sport Center",
      userId: 21901009,
      id: 323,
    },
    {
      date: "Tomorrow",
      timeSlot: "21:30 ",
      place: "Library ( Main Hall ) ",
      type: "Library",
      userId: 21901009,
      id: 3424,
    },
  ];

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
          {reservations &&
            reservations.map((reservations) => {
              return (
                <>
                  <tr className="justify-content-start">
                    <td>{reservations.date}</td>
                    <td>{reservations.timeSlot}</td>
                    <td>{reservations.place}</td>
                  </tr>
                </>
              );
            })}
        </tbody>
      </table>
    </>
  );
};
export default ReservationsList;
