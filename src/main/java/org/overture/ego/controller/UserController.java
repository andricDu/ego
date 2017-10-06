package org.overture.ego.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.overture.ego.model.entity.User;
import org.overture.ego.security.ProjectCodeScoped;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @ProjectCodeScoped
    @RequestMapping(method = RequestMethod.GET, value = "")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "List of users", response = User.class, responseContainer = "List")
            }
    )
    public @ResponseBody
    List<User> getUsersList(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) final String accessToken,
            @RequestParam(value = "offset", required = true) long offset,
            @RequestParam(value = "count", required = false) short count) {
        return null;
    }

    @ProjectCodeScoped
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "List of users", response = User.class, responseContainer = "List")
            }
    )
    public @ResponseBody
    List<User> findUsers(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) final String accessToken,
            @RequestParam(value = "query", required = true) String query,
            @RequestParam(value = "count", required = false) short count) {

        return null;
    }


    @ProjectCodeScoped
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "User Details", response = User.class)
            }
    )
    public @ResponseBody
    User getUser(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) final String accessToken,
            @PathVariable(value = "id", required = true) String userId) {
        return null;
    }


    @ProjectCodeScoped
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Updated user info", response = User.class)
            }
    )
    public @ResponseBody
    User updateUser(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) final String accessToken,
            @PathVariable(value = "id", required = true) String userId,
            @RequestBody(required = true) User updatedUserInfo) {
        return null;
    }

    @ProjectCodeScoped
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) final String accessToken,
            @PathVariable(value = "id", required = true) String userId) {
    }

}