const LocalStorageService = (function () {
  const TOKEN = "token";
  const USER = "user";
  const ROLE = "role";
  const ID = "id";

  function _setToken(token) {
    localStorage.setItem(TOKEN, token);
  }

  function _getToken() {
    return localStorage.getItem(TOKEN);
  }

  function _clearToken() {
    localStorage.removeItem(TOKEN);
  }

  function _setUser(user) {
    localStorage.setItem(USER, user);
  }

  function _getUser() {
    return localStorage.getItem(USER);
  }

  function _clearUser() {
    localStorage.removeItem(USER);
  }

  function _setRole(role) {
    localStorage.setItem(ROLE, role);
  }

  function _getRole() {
    return localStorage.getItem(ROLE);
  }

  function _clearRole() {
    localStorage.removeItem(ROLE);
  }

  function _setId(id) {
    localStorage.setItem(ID, id);
  }

  function _getId() {
    return localStorage.getItem(ID);
  }

  function _clearId() {
    localStorage.removeItem(ID);
  }

  return {
    setToken: _setToken,
    getToken: _getToken,
    clearToken: _clearToken,
    setUser: _setUser,
    getUser: _getUser,
    clearUser: _clearUser,
    setRole: _setRole,
    getRole: _getRole,
    clearRole: _clearRole,
    setId: _setId,
    getId: _getId,
    clearId: _clearId,
  };
})();

export default LocalStorageService;
