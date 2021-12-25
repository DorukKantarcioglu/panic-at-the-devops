import ReservationService from "../../service/ReservationService";
import {useEffect, useState} from "react";
import NotificationService from "../../service/NotificationService";

const NotificationList = (props) => {
  const notifications = [
    {
      header: "covid risk",
      date: "3 ",
      content: "There is a covid student in your className",
    },
    {
      header: "covid risk",
      date: "4 ",
      content: "Risky student in your cs className",
    },
  ];
  const [notification,setNotification]=useState();
  async function fetchNotifications(){

    const response = await NotificationService.getNotifications();
    {
        setNotification(response)
    }

  }
  useEffect( async () => {
    await fetchNotifications();
  }, [])

  return (
    <div>
      {notification &&
        notification.map((notification ) => {
          return (
            <a href="#" className="list-group-item list-group-item-action">
              <div className="d-flex w-100 justify-content-between">
                <h5 className="mb-1">{notification.type}</h5>
                <small> {notification.date} </small>
              </div>
              <p className="mb-1">{notification.content}</p>
            </a>
          );
        })}
    </div>
  );
};
export default NotificationList;
