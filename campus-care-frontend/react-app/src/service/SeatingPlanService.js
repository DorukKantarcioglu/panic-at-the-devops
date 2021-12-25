import axios from "axios";
import SeatingPlan from "../app/components/SeatingPlan";
import LocalStorageService from "./LocalStorageService";

const SeatingPlanService = (function () {

    const path = "http://localhost:8080/api/v1/seatingPlans";

    const _create=async (rowNo, columnNo) => {
        const response = await axios.post(path,{}, {
            headers: {
                rowNo: rowNo,
                columnNo: columnNo,
                Authorization : "Bearer " + LocalStorageService.getToken()
            }
        })
        if (response) {
            return response.data;
        }
    }

    const _getSeatingPlan=async (seatingPlanId) => {
        const response = await axios.get(path.concat("/").concat(seatingPlanId),{headers:{
                Authorization : "Bearer " + LocalStorageService.getToken()
            }});
        if (response) {
            return response.data;
        }
    }

    const _delete=async (seatingPlanId) => {
        const response = await axios.delete(path, {headers: {
            id:seatingPlanId,
                Authorization : "Bearer " + LocalStorageService.getToken()
        }});
        if (response) {
            return response.data;
        }
    }

    const _addSeating=async (props)=>{
        const response = await axios.post(path.concat("/").concat(props.seatingPlanId).concat("/seatings"),{}, {headers:{
                studentId: props.studentId,
                rowNo: props.rowNo,
                columnNo: props.columnNo,
                Authorization : "Bearer " + LocalStorageService.getToken()
            }});
        if (response) {
            return response.data;
        }
    }

    const _getSeatings=async (seatingPlanId)=>{
        const response = await axios.get(path.concat("/").concat(seatingPlanId).concat("/seatings"),{headers:{
                Authorization : "Bearer " + LocalStorageService.getToken()
            }});
        if (response) {
            return response.data;
        }
    }

    const _removeSeating =async (props)=>
    {
        const response = await axios.delete(path.concat("/").concat(props.seatingPlanId).concat("/seatings").concat(props.seatingId),
            {headers:{
                    Authorization : "Bearer " + LocalStorageService.getToken()
                }});
        if (response) {
            return response.data;
        }
    }

    return(
        {
            create: _create,
            getSeatingPlan: _getSeatingPlan,
            delete: _delete,
            addSeating: _addSeating,
            getSeatings: _getSeatings,
            removeSeating: _removeSeating
        }
    )
})();
export default SeatingPlanService;




