import React, { useEffect, useState } from "react";
import NewSeatingPlanForm from "./profileComponents/NewSeatingPlanForm";
import { Modal } from "react-bootstrap";
import { ModalBody } from "react-bootstrap";
import SeatingPlanService from "../../service/SeatingPlanService";
import CourseService from "../../service/CourseService";



export default function SeatingPlan(props) {
  const [chart, setChart] = useState([]);
  const [data, setData] = useState([]);
  const [rowNo, setRowNo] = useState(2);
  const [columnNo, setColumnNo] = useState(4);
  const [read, setRead] = useState(true);
  const [exist, setExist] = useState(false);
  const [list, setList] = useState();

  useEffect(async () => {
    await fetch();
    if (exist) {
      getSeatingPlan();
      fetchSeatingPlan();
    }
  }, [exist]);

  fetch = async (params = {}) => {
    const seatingList = await CourseService.getSeatingPlan(props.courseCode);
    setList(seatingList);
    if (seatingList != null ) setExist(true);
  };

  const getSeatingPlan = () => {
    for (let x = 0; x < list.length; x++) {
      let i = list[x].rowNo;
      let j = list[x].columnNo;
      let id = j + i * 1;

      let temp = data;
      temp[id] = list[x].student.id;
      setData(temp);
    }
  };

  const saveStudent = (event) => {
    let seatId = event.target.id;
    let studentId = event.target.value;
    let temp = data;
    temp[seatId] = studentId;
    setData(temp);
  };

  const fetchSeatingPlan = () => {
    let table = [];
    let parent = [];
    for (let i = 0; i < rowNo; i++) {
      let children = [];
      for (let j = 0; j < columnNo; j++) {
        let id = j + i * columnNo;

        children.push(
          <td>
            <input
    type="text"
    name="seat"
    id={id}
    onChange={saveStudent}
    readOnly={read}
    defaultValue={data[id]}
    />{" "}
          </td>
        );
      }
      parent.push(<tr> {children} </tr>);
    }
    table.push(<table>{parent}</table>);

    setChart(table);
  };
  const save = () => {
    setExist(true);
  };

  const updateChart = () => {
    setRead(false);
  };

  const handleChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  return (
    <div>
      <Modal show={props.show} className="seatingPlanForm">
        <div className="row justify-content-end">
          {" "}
          <button
    type="button"
    className="btn-close"
    data-dismiss="modal"
    onClick={props.handleClose}
    />
        </div>
        <ModalBody>
          {exist ? (
            <div>
              {chart}
              <button id="seatingPlanUpdate" onClick={updateChart}>
                {" "}
                Update the seating plan{" "}
              </button>
            </div>
          ) : (
            <NewSeatingPlanForm onSave={save} courseCode={props.courseCode} />
          )}
        </ModalBody>
      </Modal>
    </div>
  );
}
