const ReservationsList = ({campusmap}) => {
    return (
        <>
            <div className="row d-flex justify-content-start">
                <h5>Current Selections</h5>
            </div>
            <table
                className="border"
                style={{ position: "relative", width: "100%", marginLeft: "20px" }}
            >
                <thead>
                <tr>
                    <th> Type</th>
                    <th> Place </th>
                </tr>
                </thead>
                <tbody>
                {campusmap &&
                campusmap.map(campus =>
                    <tr>
                        <td>{campus.type}</td>
                        <td>{campus.place}</td>
                    </tr>
                )}
                </tbody>
            </table>
        </>
    );
};
export default ReservationsList;