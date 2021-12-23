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
        <div>
            <h1> Reservations List </h1>
            { reservations &&
                reservations.map((reservations) => {
                return (
                    <div>
                        <a href="#" className="list-group-item list-group-item-action">
                            <div className="d-flex w-100 justify-content-between">
                                <h5 className="mb-1">
                                    {reservations.date + " at " + reservations.timeSlot}
                                </h5>
                                <h6>
                                    {reservations.place}
                                </h6>
                            </div>
                        </a>
                    </div>
                );
            } ) }
        </div>
    );
};
export default ReservationsList;