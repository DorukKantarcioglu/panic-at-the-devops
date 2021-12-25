import axios from "axios";
import LocalStorageService from "./LocalStorageService";

const ReservationService = (function () {

  const path = "http://localhost:8080/api/v1/reservations";

  const _getReservations = async () => {

    const response = await axios.get(
      "http://localhost:8080/api/v1/reservations",{Headers:{Authorization: "Bearer " + LocalStorageService.getToken()}}
    );
    if (response) {
      return response.data;
    }
  };

  const _getReservation = async (id) => {
    const url = "http://localhost:8080/api/v1/reservations/" + id;
    const response = await axios.get(url, {headers:{Authorization : "Bearer " + LocalStorageService.getToken()}});
    if (response) {
      return response.data;
    }
  };

  const _createReservation = async (reservation) => {
    const response = await axios.post(
      "http://localhost:8080/api/v1/reservations",
      {},
      {
        headers: {
          userId: reservation.userId,
          date: reservation.date,
          timeSlot: reservation.timeSlot,
          place: reservation.place,
          type: reservation.type,
          Authorization : "Bearer " + LocalStorageService.getToken()
        },
      }
    );
    if (response) {
      return response.data;
    }
  };

  const _deleteReservation = async (id) => {
    const url = "http://localhost:8080/api/v1/reservations/" + id;
    const response = await axios.delete(url, {headers:{Authorization : "Bearer " + LocalStorageService.getToken()}});
    if (response) {
      return response.data;
    }
  };

  const _getAvailableTimes=async (type, date, place) => {
    const response = await axios.get(path.concat("/timeSlot"), {
      headers: {
        type: type,
        date: date,
        place: place,
        Authorization : "Bearer " + LocalStorageService.getToken()
      }
    });
    if (response) {
      console.log("This is the return of the available dates: ", response.data)
      return response.data;
    }else {
      console.log("Nothing returned")
    }
  }

  const _getPlaces=async (type) => {
    const response = await axios.get(path.concat("/places"), {
      headers: {
        type: type,
        Authorization : "Bearer " + LocalStorageService.getToken()
      }
    });
    if (response) {
      return response.data;
    }
  }

  return {
    getReservations: _getReservations,
    getReservation: _getReservation,
    createReservation: _createReservation,
    deleteReservation: _deleteReservation,
    getAvailableTimes: _getAvailableTimes,
    getPlaces: _getPlaces
  };
})();
export default ReservationService;

