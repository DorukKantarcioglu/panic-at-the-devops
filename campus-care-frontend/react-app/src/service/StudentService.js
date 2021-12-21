import axios from "axios";

const StudentService = (function () {

    const _fetchAllStudents = async()=>{
        const response = await axios.get("http://localhost:8080/api/v1/students");
        if (response) {
            return response.data;
        }

    };

    const _getStudentById = async (id)=>{
        const url = "http://localhost:8080/api/v1/students/".concat(id)
        const response = await axios.get(url)
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
                "eGovernmentPassword": eGovernmentPassword
            }})
        if (response) {
              return response.data;
        }
    }

    const _createStudent = async()=>{
        const response = await axios.post("http://localhost:8080/api/v1/students");
        if (response) {
            return response.data;
        }

    };

    const _updateHesCode = async (id, hesCode)=>{
        const url = "http://localhost:8080/api/v1/students/" + id
        const response = await axios.patch(url,{}, {headers: {
                "hesCode": hesCode
            }})
        if (response) {
            return response.data;
        }
    }

    const _deleteStudent = async (id)=>{
        const url = "http://localhost:8080/api/v1/students/" + id
        const response = await axios.delete(url)
        if (response) {
            return response.data;
        }
    }

    return {
        fetchAllStudents: _fetchAllStudents,
        getStudentById: _getStudentById,
        createStudent: _createStudent,
        getStudentByHesCode: _getStudentByHesCode,
        updateHesCode: _updateHesCode,
        deleteStudent: _deleteStudent,
        validateHesCode: _validateHesCode
    };
})();
export default StudentService;