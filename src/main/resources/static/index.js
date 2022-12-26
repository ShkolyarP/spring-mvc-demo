angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null

            }
        })
            .then(function (response) {
                $scope.ProductList = response.data.content;
                $scope.filter = null
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete(contextPath + '/products/' + id)
            .then(function (response) {
                $scope.loadProducts();
            });
    }


    $scope.addProduct = function () {
        console.log($scope.newProduct)
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.loadProducts();
                $scope.newProduct = null;
            });
    }


    $scope.loadProducts();
});