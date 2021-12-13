import StudentService from "../service/StudentService";
import StudentList from "./components/StudentList";
import {useEffect, useState} from "react";

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
        <StudentList data = {data}/>
    )

}

export default CoursePage