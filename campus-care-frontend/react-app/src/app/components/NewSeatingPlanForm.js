import React from "react";

class NewSeatingPlanForm extends React.Component
{
    constructor(props) {
        super(props);
        this.state = {
            rowNo:'3',
            columnNo: '1',
            capacity:'',
            table: ''
        }
    }

    createSeatingPlan=()=>
    {
        let table = '<table>'

        for (let i = 1; i <= this.state.rowNo; i++)
        {
            table += '<tr>'
            for (let j = 1; j <= this.state.columnNo; j++)
            {
                table += '<td> <input type="text"  onChange={}/> </td>'
            }
            table += '<tr/>'
        }
        table += '<table/>'
        this.setState({table: {table}})


    }
    componentDidMount() {
        this.createSeatingPlan()
        //console.log({this.this.state.})
    }

    handleChange=(event)=>
    {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render(){
        return(

            <div>
                <label className="seatingPlanForm"> Please enter the classroom capacity: </label>
                <br/>
                <input type="text" id="capacity" name="capacity" value= {this.state.capacity}onChange={this.handleChange}/>
                <label className="seatingPlanForm" id ="rowNo"> Number of rows: </label>
                <input type="text" id="rowNo" name="rowNo" value= {this.state.rowNo} onChange={this.handleChange}/>
                <br/>
                <label className="seatingPlanForm" id ="columnNo"> Number of seats in the rows: </label>
                <input type="text" id="columnNo" name="columnNo" value= {this.state.columnNo}onChange={this.handleChange}/>
                <button id = "seatingPlanSubmit" onClick={this.createSeatingPlan}> Create the seating plan </button>
                {document.write(this.state.table)}
            </div>

        )
    }


}
export default NewSeatingPlanForm