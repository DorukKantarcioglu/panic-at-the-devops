import * as ReactDOM from "react-dom";
import InfoBox from "./InfoBox";

const StudentList = (props) => {
  const handleShow = (id) => {
    const path = "/student-info/".concat(id);
    console.log(path);
    //history.back()
    ReactDOM.render(<InfoBox id={id} />, document.getElementById("root"));
  };

  return (
    <table className="justify-content-start">
      <thead>
        <tr>
          <th> Name</th>
          <th> Surname</th>
          <th> ID Number</th>
          <th>Risk Status</th>
        </tr>
      </thead>
      <tbody>
        {props.data &&
          props.data.map((student) => {
            return (
              <>
                <tr>
                  <td>{student.name}</td>
                  <td>{student.surname}</td>
                  <td>{student.id}</td>
                  <td>{student.allowedOnCampus}</td>
                  <td>
                    <button onClick={() => handleShow(student.id)}>show</button>{" "}
                  </td>
                </tr>
              </>
            );
          })}
      </tbody>
    </table>
  );
};
export default StudentList;
