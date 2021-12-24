import axios from "axios";

const NotificationService = (function () {
  const _getNotifications = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/notifications"
    );
    if (response) {
      return response.data;
    }
  };

  const _getNotification = async (id) => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/notifications/".concat(id)
    );
    if (response) {
      return response.data;
    }
  };

  const _getRandomMotivationalQuote = async (id) => {
    const response = await axios.get(
      "http://localhost:8080/api/v1/notifications/motivationalQuotes"
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
