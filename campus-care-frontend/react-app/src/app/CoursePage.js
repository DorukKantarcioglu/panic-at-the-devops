import StudentService from "../service/StudentService";
import StudentList from "./components/profileComponents/StudentList";
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
        </div>
    )

}

export default CoursePage