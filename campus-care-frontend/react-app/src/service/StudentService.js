import axios from "axios";
import LocalStorageService from "./LocalStorageService";
import {notification} from "antd";

const StudentService = (function () {

    const _fetchAllStudents = async()=>{
        const response = await axios.get("http://localhost:8080/api/v1/students",{headers:{
                Authorization : "Bearer " + LocalStorageService.getToken()
            }});
        if (response) {
            return response.data;
        }

    };

    const _getStudentById = async (id)=>{
        const url = "http://localhost:8080/api/v1/students/"+id
        const response = await axios.get(url,{headers:{
                Authorization : "Bearer " + LocalStorageService.getToken()
            }})
        if (response) {
            return response.data;
        }
    }

    const _getStudentByHesCode = async (hesCode)=>{
        const response = await axios.get("http://localhost:8080/api/v1/students", { headers:{
            "hesCode": {hesCode}.toString()
            }})
        if (response) {
            return response.data;
        }
    }

    const _validateHesCode = async (hesCode, trIdNumber, eGovernmentPassword)=>{


        const response = await axios.post("http://localhost:8080/api/v1/students",{}, {headers: {
                "hesCode": hesCode,
                "tridNumber": trIdNumber,
                "eGovernmentPassword": eGovernmentPassword,

            Authorization : "Bearer " + LocalStorageService.getToken()

            }})
        if (response) {
              return response.data;
        }
    }

    const _createStudent = async()=>{
        const response = await axios.post("http://localhost:8080/api/v1/students",[],{headers:{
                Authorization : "Bearer " + LocalStorageService.getToken()
            }});
        if (response) {
            return response.data;
        }

    };

    const _updateHesCode = async (id, hesCode)=>{
        const url = "http://localhost:8080/api/v1/students/" + id
        const response = await axios.patch(url,{}, {headers: {
                "hesCode": hesCode,
                Authorization : "Bearer " + LocalStorageService.getToken()
            }})
        if (response) {
            return response.data;
        }
    }

    const _deleteStudent = async (id)=>{
        const url = "http://localhost:8080/api/v1/students/" + id
        const response = await axios.delete(url,{headers:{
                Authorization : "Bearer " + LocalStorageService.getToken()
            }} )
        if (response) {
            return response.data;
        }
    }

    const _getNotifications=async (id) => {
        const url = "http://localhost:8080/api/v1/students/" + id + "/notifications"
        const response = await axios.get(url, {
            headers: {
                Authorization: "Bearer " + LocalStorageService.getToken()
            }
        })
    }


    return {
        fetchAllStudents: _fetchAllStudents,
        getStudentById: _getStudentById,
        createStudent: _createStudent,
        getStudentByHesCode: _getStudentByHesCode,
        updateHesCode: _updateHesCode,
        deleteStudent: _deleteStudent,
        validateHesCode: _validateHesCode,
        getNotifications: _getNotifications
    };
})();
export default StudentService;


