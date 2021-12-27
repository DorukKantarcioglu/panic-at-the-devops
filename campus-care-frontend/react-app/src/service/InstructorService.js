import axios from "axios";
import LocalStorageService from "./LocalStorageService";
import StudentService from "./StudentService";

const InstructorService =(function () {
    
    const _getAllInstructors=async()=>
    {
        const response = await axios.get("http://localhost:8080/api/v1/instructors");
        if (response) {
            return response.data;
        }
    }

    const _createInstructor=async(instructor)=>
    {
        const response = await axios.post("http://localhost:8080/api/v1/instructors", {
            id: instructor.id,
            name: instructor.name,
            email: instructor.email,
            hesCode: instructor.hesCode,
            coursesGiven: instructor.coursesGiven
        });
        if (response) {
            return response.data;
        }
    }

    const _getInstructorById=async(id)=>
    {
        const url = "http://localhost:8080/api/v1/instructors/".concat(id);
        const response = await axios.get(url, {headers:{Authorization : "Bearer " + LocalStorageService.getToken()}})
        if (response) {
            return response.data;
        }
        else {
            console.log("else")
            return StudentService.getStudentById(id);
        }
    }
    
    const _getInstructorByHesCode=async(hesCode)=>
    {
        const response = await axios.get("http://localhost:8080/api/v1/instructors", { headers:{
                "hesCode": {hesCode}.toString(),
                Authorization : "Bearer " + LocalStorageService.getToken()
            }});
        if (response) {
            return response.data;
        }
    }

    const _updateHesCode = async (id, hesCode)=>{
        const url = "http://localhost:8080/api/v1/instructors/".concat(id)
        const response = await axios.patch(url,{}, {headers: {
                "hesCode": hesCode,
                Authorization : "Bearer " + LocalStorageService.getToken()
            }});
        if (response) {
            return response.data;
        }
    }

    const _getNotAllowedStudents =async(courseCode, id)=>
    {
        const url = "http://localhost:8080/api/v1/instructors/".concat(id);
        const response = await axios.get(url,{}, {headers: {
                "courseCode": courseCode,
                Authorization : "Bearer " + LocalStorageService.getToken()
            }});
        if (response) {
            return response.data;
        }
    }

    const _deleteInstructor = async (id)=>{
        const url = "http://localhost:8080/api/v1/instructors/".concat(id);
        const response = await axios.delete(url, {headers:{Authorization : "Bearer " + LocalStorageService.getToken()}})
        if (response) {
            return response.data;
        }
    }

    const _addCourse =async(courseCode, id)=>{
        const url = "http://localhost:8080/api/v1/instructors/".concat(id);
        const response = await axios.patch(url,{}, {headers: {
                "courseCode": courseCode,
                Authorization : "Bearer " + LocalStorageService.getToken()
            }});
        if (response) {
            return response.data;
        }
    }

    const _deleteCourse =async(courseCode, id)=>{
        const url = "http://localhost:8080/api/v1/instructors/".concat(id);
        const response = await axios.delete(url,{}, {headers: {
                "courseCode": courseCode,
                Authorization : "Bearer " + LocalStorageService.getToken()
            }});
        if (response) {
            return response.data;
        }
    }

    const _getCourses=async (id) => {
        const url = "http://localhost:8080/api/v1/instructors/".concat(id).concat("/courses");
        const response = await axios.get(url, {headers:{
                Authorization : "Bearer " + LocalStorageService.getToken()
            }});
        if (response) {
            return response.data;
        }
    }
    const _getNotifications=async (id) => {
        const url = "http://localhost:8080/api/v1/instructors/" + id + "/notifications"
        const response = await axios.get(url, {
            headers: {
                Authorization: "Bearer " + LocalStorageService.getToken()
            }
        })
        if (response) {
            return response.data;
        }
    }

    const _getReservations=async (id)=>{
        const url = "http://localhost:8080/api/v1/instructors/" + id + "/reservations"
        const response = await axios.get(url, {
            headers: {
                Authorization: "Bearer " + LocalStorageService.getToken()
            }
        })
        if (response) {
            return response.data;
        }
    }

    return(
        {
            createInstructor: _createInstructor,
            getAllInstructors: _getAllInstructors,
            getInstructorById: _getInstructorById,
            getInstructorByHesCode: _getInstructorByHesCode,
            updateHesCode: _updateHesCode,
            getNotAllowedStudents: _getNotAllowedStudents,
            deleteInstructor: _deleteInstructor,
            addCourse: _addCourse,
            deleteCourse: _deleteCourse,
            getCourses: _getCourses,
            getNotifications: _getNotifications,
            getReservations: _getReservations
        }
    );

})();
export default InstructorService;

