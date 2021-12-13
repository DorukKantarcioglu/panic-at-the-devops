import StudentService from "../service/StudentService";
import StudentList from "./components/StudentList";
import {useEffect, useState} from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';



const CoursePage =()=>
{
    const [data, setData] = useState([])
    const  data2 = [{name:"Elif"},{name:"Ece"}, {name:"Elif"} ]

    const fetchData=async () => {
            setData(await StudentService.fetchAllStudents())
    }

    useEffect(()=>{
        console.log("useEff")
        fetchData().then()
    },[])

    return(
        <div>
            <StudentList data = {data}/>
            hello

         <i
                className="fas fa-arrow-right"
                style={{ fontSize: "20pt", color: "#29377e" }}
              ></i>


    
            <FontAwesomeIcon icon="check-square"> yağmur </FontAwesomeIcon>
              hello
        </div>
    )

}

export default CoursePage