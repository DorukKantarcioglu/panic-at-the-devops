import * as ReactDOM from "react-dom";
import InfoBox from "./InfoBox";
import {useEffect, useState} from "react";
import CourseService from "../../../service/CourseService";

const StudentList = (props) => {

  const [riskColor, setColor] = useState(false);
  const [data, setData] = useState();

  const handleShow = (id) => {
    const path = "/student-info/".concat(id);
    console.log(path);
    //history.back()
    ReactDOM.render(<InfoBox id={id} />, document.getElementById("root"));
  };

  useEffect(async () => {
    await fetch();
  })

  const fetch=async () => {
    const list = await CourseService.getStudentList(props.courseCode);
    setData(list)
  }
  const students = [
    {
      name: "Yağmur",
      surname: "Eryılmaz",
      id: "21903224",
      riskStatus: "risky",
    },
    {
      name: "Elif",
      surname: "Çenesiz",
      id: "21902432",
      riskStatus: "riskless",
    },
  ];

  const checkRiskStatus = (status) => {
    let bgRisky = "#f0bac1";
    let bgRiskless = "#caf5d0";
    if (status === "risky") {
      return bgRisky;
    } else {
      return bgRiskless;
    }
  };

  return (
    <table
      className="border"
      style={{ position: "relative", width: "100%", marginLeft: "0px" }}
    >
      <thead>
        <tr>
          <th> Name</th>
          <th> Surname</th>
          <th> ID Number</th>
          <th>Risk Status</th>
          <th>Student Info</th>
        </tr>
      </thead>
      <tbody>
        {data &&
          data.map((student) => {
            return (
              <>
                <tr>
                  <td>{student.name}</td>
                  <td>{student.surname}</td>
                  <td>{student.id}</td>
                  <td
                    style={{
                      backgroundColor: checkRiskStatus(student.riskStatus),
                    }}
                  >
                    {student.riskStatus}
                  </td>
                  <td>
                    <button
                      style={{ width: "100%" }}
                      className="button"
                      onClick={() => handleShow(student.id)}
                    >
                      show
                    </button>{" "}
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
