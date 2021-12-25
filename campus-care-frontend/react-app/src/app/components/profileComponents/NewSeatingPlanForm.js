import React from "react";

import CourseService from "../../../service/CourseService";
import SeatingPlanService from "../../../service/SeatingPlanService";

class NewSeatingPlanForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      rowNo: "",
      columnNo: "",
      capacity: "",
      table: "",
      chart: [],
      id: null,
    };
  }

  saveStudent = (event) => {
    let seatId = event.target.id;
    let studentId = event.target.value;
    let temp = this.state.chart;
    temp[seatId] = studentId;
    this.setState({ chart: temp });
  };

  save = async () => {
    for (let i = 0; i < this.state.rowNo; i++) {
      for (let j = 0; j < this.state.columnNo; j++) {
        let id = j + i * this.state.columnNo;
        await SeatingPlanService.addSeating({
          seatingPlanId: this.state.id,
          studentId: this.state.chart[id],
          rowNo: i,
          columnNo: j,
        });
      }
    }
  };

  createSeatingPlan = async () => {
    let seatingPlan = await SeatingPlanService.create(
      this.state.rowNo,
      this.state.columnNo
    ).then();

    this.setState({ id: seatingPlan.id });
    await CourseService.addSeatingPlan(this.state.id, this.props.courseCode);
    console.log("hello");
    let table = [];
    let parent = [];
    for (let i = 0; i < this.state.rowNo; i++) {
      let children = [];
      for (let j = 0; j < this.state.columnNo; j++) {
        let id = j + i * this.state.columnNo;
        children.push(
          <td>
            <input
              type="text"
              name="seat"
              id={id}
              onChange={this.saveStudent}
            />
          </td>
        );
      }
      parent.push(<tr> {children} </tr>);
    }
    table.push(<div className="row justify-content-start">

                <table className="" style={{position:"relative",width:"30%"}}>{parent}</table>
    </div>);
    this.setState({ table: table });
  };

  handleChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  render() {
      return (
          <div className="seatingPlanForm">
              <label className="seatingPlanForm m-2" id="rowNo">
                  {" "}
                  Number of rows:{" "}
              </label>
              <input
                  type="text"
                  id="rowNo"
                  name="rowNo"
                  value={this.state.rowNo}
                  onChange={this.handleChange}
              />
              <br />
              <label className="seatingPlanForm m-2" id="columnNo">
                  {" "}
                  Number of seats:{" "}
              </label>
              <input
                  type="text"
                  id="columnNo"
                  name="columnNo"
                  value={this.state.columnNo}
                  onChange={this.handleChange}
              />
              <button
                  className="button m-3"
                  id="seatingPlanSubmit"
                  onClick={this.createSeatingPlan}
              >
                  {" "}
                  Create the seating plan{" "}
              </button>
              <button className="button m-3" id="seatingPlanSave" onClick={this.save}>
                  Save
              </button>
              {this.state.table}


          </div>
      );
  }
}
export default NewSeatingPlanForm;
