import axios from "axios";

const ReservationService = (function () {
  const _getReservations = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/reservations"
    );
    if (response) {
      return response.data;
    }
  };

  const _getReservation = async (id) => {
    const url = "http://localhost:8080/api/v1/reservations/" + id;
    const response = await axios.get(url);
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
        },
      }
    );
    if (response) {
      return response.data;
    }
  };

  const _deleteReservation = async (id) => {
    const url = "http://localhost:8080/api/v1/reservations/" + id;
    const response = await axios.delete(url);
    if (response) {
      return response.data;
    }
  };

  return {
    getReservations: _getReservations,
    getReservation: _getReservation,
    createReservation: _createReservation,
    deleteReservation: _deleteReservation,
  };
})();
export default ReservationService;
