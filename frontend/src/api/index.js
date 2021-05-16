import { API } from "../backend";

export function signin(username) {
  // console.log({ username });
  // console.log(`${API}exercise/all/${username}`);
  return fetch(`${API}exercise/all/${username}`, {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    // body: JSON.stringify(username),
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.log(err));
}

export function getUser(username) {
  return fetch(`${API}/user/findBy/${username}`, {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.error(err));
}

export function signup(userDetails) {
  return fetch(`${API}user/add`, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userDetails),
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.error(err));
}

export function deleteExercise(id) {
  console.log(`${API}exercise/deleteBy/${id}`);
  return fetch(`${API}exercise/deleteBy/${id}`, {
    method: "DELETE",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.error(err));
}

export function addExercise(exerciseDetails, id) {
  return fetch(`${API}exercise/add/${id}`, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(exerciseDetails),
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => console.error(err));
}
