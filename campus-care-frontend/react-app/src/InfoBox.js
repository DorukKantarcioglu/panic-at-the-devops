import React from "react";
import {useEffect} from "react";
import StudentService from "./StudentService";


class InfoBox extends React.Component{

     constructor(props) {
        super(props);
        this.state = {
            student: {
                id: null,
                name: null,
                email: null,
                hesCode: null,
                allowedOnCampus: null,
                vaccinated: null,
                tested: null,
                newHesCode:''
            }
        }
    }

   handleChange=(event)=> {
        this.setState({newHesCode:event.target.value});

    }

    changeHesCode=async () => {
        await StudentService.updateHesCode(this.state.student.id, this.state.newHesCode)

    }

    async componentDidMount() {
        this.setState({student: await StudentService.getStudentById(this.props.id)})
    }

    render(){
         return (
             <>
                 <div className='infoBox'>
                    <label className='infoBox'> Name: {this.state.student.name} </label>
                     <br/>
                     <label className='infoBox'> Email: {this.state.student.email} </label>
                     <br/>
                     <label className='infoBox'> Hes Code: {this.state.student.hesCode} </label>
                     <br/>
                     <label className='infoBox'> isAllowed: {this.state.student.allowedOnCampus} </label>
                     <br/>
                     <label className='infoBox'> vaccinated: {this.state.student.vaccinated} </label>
                     <br/>

                 </div>
                 <div className='updateHesCode'>
                     <label> Enter your recent HES code</label>
                     <input type="text" id="hesCode" name="hesCode" onChange={this.handleChange} />
                     <button onClick={this.changeHesCode}> Update </button>
                 </div>
             </>
         );
    }
}

export default InfoBox