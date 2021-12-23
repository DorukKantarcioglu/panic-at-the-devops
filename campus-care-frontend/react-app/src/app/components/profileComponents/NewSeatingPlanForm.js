import React from "react";

class NewSeatingPlanForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      rowNo: "",
      columnNo: "",
      capacity: "",
      table: "",
      chart: [],
    };
  }

  saveStudent = (event) => {
    let seatId = event.target.id;
    let studentId = event.target.value;
    let temp = this.state.chart;
    temp[seatId] = studentId;
    this.setState({ chart: temp });
  };

  createSeatingPlan = () => {
      let table = [];
      let parent = [];
      for (let i = 0; i <= this.state.rowNo; i++)
      {
          let children = [];
          for (let j = 0; j <= this.state.columnNo; j++)
          {
              let id = j+(i*this.state.columnNo);
              if (j === 0)
                  children.push(<td> <label type="text" name="seat" /> {i}</td>)
              if (i === 0)
                  children.push(<td> <label type="text" name="seat" /> {String.fromCharCode(j+97)}</td>)
              else
                  children.push(<td> <input type="text" name="seat" id = {id}  onChange={this.saveStudent} /> </td>);
          }
          parent.push(<tr> {children} </tr>);
      }
      table.push(<table>{parent}</table>)



      this.setState({table:table});
  };


  handleChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };


  render() {
    return (
      <div className="seatingPlanForm">

        <label className="seatingPlanForm" id="rowNo">
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
        <label className="seatingPlanForm" id="columnNo">
          {" "}
          Number of seats in the rows:{" "}
        </label>
        <input
          type="text"
          id="columnNo"
          name="columnNo"
          value={this.state.columnNo}
          onChange={this.handleChange}
        />
        <button id="seatingPlanSubmit" onClick={this.createSeatingPlan}>
          {" "}
          Create the seating plan{" "}
        </button>
        {this.state.table}
          <button id = "seatingPlanSave" onClick ={this.props.onSave} >Save</button>
      </div>
    );
  }
}
export default NewSeatingPlanForm;
