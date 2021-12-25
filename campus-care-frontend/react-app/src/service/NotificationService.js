import axios from "axios";
import LocalStorageService from "./LocalStorageService";

const NotificationService = (function () {
  const _getNotifications = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/notifications",
        {
          headers: {
            Authorization: "Bearer " + LocalStorageService.getToken()
          }}
    );
    if (response) {
      return response.data;
    }
  };

  const _getNotification = async (id) => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/notifications/".concat(id),
        {
          headers: {
            Authorization: "Bearer " + LocalStorageService.getToken()
          }}
    );
    if (response) {
      return response.data;
    }
  };

  const _getRandomMotivationalQuote = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/notifications/motivationalQuotes",{
          headers: {
            Authorization: "Bearer " + LocalStorageService.getToken()
          }}
    );
    if (response) {
      return response.data;
    }
  };

  const _createMotivationalQuote = async (motivationalQuote) => {
    const response = await axios.post(
      "http://localhost:8080/api/v1/notifications",
      {},
      {
        headers: {
          content: motivationalQuote.content,
        },
      }
    );
    if (response) {
      return response.data;
    }
  };

  const _deleteNotification = async (id) => {
    const url = "http://localhost:8080/api/v1/notifications/" + id;
    const response = await axios.delete(url);
    if (response) {
      return response.data;
    }
  };
  return {
    getNotifications: _getNotifications,
    getNotification: _getNotification,
    getRandomMotivationalQuote: _getRandomMotivationalQuote,
    _createMotivationalQuote: _createMotivationalQuote,
    _deleteNotification: _deleteNotification,
  };
})();

export default NotificationService;
