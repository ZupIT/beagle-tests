const StorageService = {
  getData: (key: string) => {
    return JSON.parse(localStorage.getItem(key));
  },

  setData: (key: string, data: any) => {
    localStorage.setItem(key, JSON.stringify(data));
  }
}

export default StorageService